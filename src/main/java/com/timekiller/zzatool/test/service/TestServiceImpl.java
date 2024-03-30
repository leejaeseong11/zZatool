package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dao.TestRepositoryCustom;
import com.timekiller.zzatool.test.dto.TestCreateDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    // Logger 인스턴스 생성
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    private final TestRepository testRepository;
    private final AwsS3Service awsS3Service;
    private final String testImageUploadPath = "/test-image";
    private final TestRepositoryCustom testRepositoryCustom;

    @Override
    public Page<TestDTO> findTestList(Integer testStatus, Pageable pageable) {
        List<TestDTO> selectedTestList = new ArrayList<>();
        Page<Test> selectedTestPage =
                testRepository.findByTestStatusOrderByTestDateDesc(1, pageable);

        for (Test selectedTest : selectedTestPage) {
            selectedTestList.add(testEntityToDTO(selectedTest));
        }

        return new PageImpl<>(selectedTestList, pageable, selectedTestPage.getTotalElements());
    }

    private TestDTO testEntityToDTO(Test testEntity) {
        return TestDTO.builder()
                .testId(testEntity.getTestId())
                .testTitle(testEntity.getTestTitle())
                .memberId(testEntity.getMemberId())
                .testDate(testEntity.getTestDate())
                .testImage(testEntity.getTestImage())
                .testCount(testEntity.getTestCount())
                .testStatus(testEntity.getTestStatus())
                .build();
    }

    @Override
    public void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception {
        try {
            if (!Objects.isNull(testImage)) {
                String imageUrl = awsS3Service.uploadImage(testImage, testImageUploadPath);
                testCreateDTO.setTestImage(imageUrl);
            }

            Test test =
                    Test.builder()
                            .testTitle(testCreateDTO.getTestTitle())
                            .testDate(testCreateDTO.getTestDate())
                            .testImage(testCreateDTO.getTestImage())
                            .memberId(testCreateDTO.getMemberId())
                            .build();

            testRepository.save(test);
        } catch (Exception e) {
            logger.log(Level.INFO, "테스트 생성 실패", e);
            throw new Exception("테스트 생성 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteTest(Long testId, Long memberId) throws RemoveException {
        Optional<Test> optionalTest = testRepository.findById(testId);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            if (test.getMemberId().equals(memberId)) {
                if (test.getTestCount() == 0L) {
                    testRepository.deleteById(testId);
                    awsS3Service.deleteImage(test.getTestImage(), testImageUploadPath);
                } else {
                    throw new RemoveException("테스트를 삭제할 수 없습니다.");
                }
            }
        }
    }
}
