package com.timekiller.zzatool.result.service;

import com.timekiller.zzatool.result.dto.ResultDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResultService {

    /**
     * 회원이 푼 테스트 결과 목록 조회한다.
     *
     * @param memberId 회원 아이디
     * @return 테스트 결과 목록
     */
    Page<ResultDTO> findResultListByMemberId(Long memberId, Pageable pageable);
}
