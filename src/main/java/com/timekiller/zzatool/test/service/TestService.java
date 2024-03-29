package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.TestCreateDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface TestService {

    /* 테스트 상태에 따른 전체 목록 조회 */
    Page<TestDTO> findTestList(Integer testStatus, Pageable pageable);

    /**
     * 사용자가 테스트를 생성할 수 있다.
     *
     * @param testDTO   테스트
     * @param testImage 테스트 이미지
     * @throws Exception
     */
    void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception;

    /**
     * 테스트를 푼 회원이 없는 경우 작성자가 테스트를 삭제할 수 있다.
     *
     * @param testId 테스트 아이디
     * @throws RemoveException
     */
    void deleteTest(Long testId, Long memberId) throws RemoveException;
}
