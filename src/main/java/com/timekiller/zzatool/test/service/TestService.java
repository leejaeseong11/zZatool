package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface TestService {

    /**
     * 테스트 생성
     *
     * @param testDTO   테스트
     * @param testImage 테스트 대표 이미지
     */
    void createTest(TestDTO testDTO, MultipartFile testImage) throws Exception;
}
