package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {
    /* 테스트 상태에 따른 전체 목록 조회 */
    Page<TestDTO> findTestList(Integer testStatus, Pageable pageable);

    /* 관리자의 테스트 수정 */
    void modifyTest(Integer testId, TestDTO testDTO);

    /* 관리자의 테스트 삭제 */
    void removeTest(Integer testId);
}
