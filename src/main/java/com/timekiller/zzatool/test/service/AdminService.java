package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.TestDTO;

public interface AdminService {

    /* 관리자의 테스트 수정 */
    void modifyTest(Integer testId, TestDTO testDTO);

    /* 관리자의 테스트 삭제 */
    void removeTest(Integer testId);
}
