package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.MyTestDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService {

    /* 테스트 상태에 따른 전체 목록 조회 */
    Page<TestDTO> findTestList(Pageable pageable, Integer testStatus);

    /* 테스트 검색 및 정렬 */
    List<TestDTO> findSearchTestList(
            int page, int size, Integer testStatus, String search, String sort, String date);

    /* 테스트 상세 조회 */
    TestDTO findTest(Long testId);

    /* 검색된 테스트 개수 */
    Long countSearchTest(Integer testStatus, String search, String sort, String date);

    /* 사용자가 테스트를 생성 */
    Test createTest(TestDTO testDTO) throws Exception;

    /* 테스트 수정 */
    void updateTest(Long testId, TestDTO testDTO) throws Exception;

    //    /**
    //     * 사용자가 테스트를 생성할 수 있다.
    //     *
    //     * @param testCreateDTO 테스트
    //     * @param testImage 테스트 이미지
    //     * @throws Exception
    //     */
    //    void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception;

    /**
     * 테스트를 푼 회원이 없는 경우 작성자가 테스트를 삭제할 수 있다.
     *
     * @param testId 테스트 아이디
     * @throws RemoveException
     */
    void deleteTest(Long testId, Long memberId) throws RemoveException;

    /**
     * 회원이 만든 테스트 목록 중 페이지에 해당하는 목록을 조회한다.
     *
     * @param memberId 회원 아이디
     * @param order 정렬 기준: date (최신순=default), count (조회순)
     * @param pageable 페이지
     * @return 페이지에 해당하는 테스트 목록
     * @throws Exception sqlException 또는 정렬 기준 오류
     */
    Page<MyTestDTO> findTestListByMemberId(Long memberId, String order, Pageable pageable)
            throws Exception;

    /**
     * 테스트 아이디에 해당하는 테스트 정보를 조회한다.
     *
     * @param testId 테스트 아이디
     * @return 테스트 정보
     */
    TestDTO findTestByTestId(Long testId) throws Exception;
}
