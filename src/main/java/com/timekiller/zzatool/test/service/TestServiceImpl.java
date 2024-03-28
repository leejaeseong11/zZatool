package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dto.TestCreateDTO;
import com.timekiller.zzatool.test.entity.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
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
    public void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception {
        try {
            if (!Objects.isNull(testImage)) {
                String imageUrl = awsS3Service.uploadImage(testImage, profileImageUploadPath);
                testCreateDTO.setTestImage(imageUrl);
            }

            Test test = Test.builder()
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
}
