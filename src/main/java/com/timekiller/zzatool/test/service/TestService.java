package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestCreateDTO;
import org.springframework.web.multipart.MultipartFile;

public interface TestService {

    /**
     * 테스트 생성
     *
     * @param test      테스트
     * @param mainImage 테스트 대표 이미지
     */
    void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception;
}
