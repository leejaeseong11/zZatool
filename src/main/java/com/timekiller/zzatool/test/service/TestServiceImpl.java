package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final String profileImageUploadPath = "/test-image";

    @Override
    public void createTest(TestDTO testDTO, MultipartFile testImage) throws Exception {
        try {
            if (!Objects.isNull(testImage)) {
                String imageUrl = awsS3Service.uploadImage(testImage, profileImageUploadPath);
                testDTO.setTestImage(imageUrl);
            }

            Test test = Test.builder()
                    .testTitle(testDTO.getTestTitle())
                    .testDate(testDTO.getTestDate())
                    .testImage(testDTO.getTestImage())
                    .memberId(testDTO.getMemberId())
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
                    awsS3Service.deleteImage(test.getTestImage(), profileImageUploadPath);
                } else {
                    throw new RemoveException("테스트를 삭제할 수 없습니다.");
                }
            }
        }
    }
}
