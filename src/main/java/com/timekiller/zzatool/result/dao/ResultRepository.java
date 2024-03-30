package com.timekiller.zzatool.result.dao;

import com.timekiller.zzatool.result.entity.Result;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    /**
     * memberId에 해당하는 결과 목록을 resultDate desc로 정렬하여 pageable만큼 조회
     *
     * @param memberId 회원 아이디
     * @param pageable 페이지
     * @return 결과 목록
     */
    List<Result> findByMemberIdOrderByResultDateDesc(Long memberId, Pageable pageable);
}
