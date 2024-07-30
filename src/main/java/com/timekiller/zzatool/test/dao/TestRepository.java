package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    Page<Test> findByTestStatusOrderByTestDateDesc(Integer testStatus, Pageable pageable);

    /**
     * memberId에 해당하는 테스트 목록을 testDate desc로 정렬하여 pageable만큼 조회
     *
     * @param memberId 회원 아이디
     * @param pageable 페이지
     * @return 테스트 목록
     */
    List<Test> findByMemberIdOrderByTestDateDesc(Long memberId, Pageable pageable);

    /**
     * memberId에 해당하는 테스트 목록을 testCount desc로 정렬하여 pageable만큼 조회
     *
     * @param memberId 회원 아이디
     * @param pageable 페이지
     * @return 테스트 목록
     */
    List<Test> findByMemberIdOrderByTestCountDesc(Long memberId, Pageable pageable);
}
