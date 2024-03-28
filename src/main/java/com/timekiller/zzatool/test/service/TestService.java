package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {
    /* 테스트 상태에 따른 전체 목록 조회 */
    Page<TestDTO> findTestList(Integer testStatus, Pageable pageable);
}
