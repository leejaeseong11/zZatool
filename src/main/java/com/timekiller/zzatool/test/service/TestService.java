package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {
    /* 테스트 상태에 따른 전체 목록 조회 */
    Page<TestDTO> findTestList(Pageable pageable, Integer testStatus);

    List<TestDTO> findSearchTestList(
            int page, int size, Integer testStatus, String search, String sort, String date);
}
