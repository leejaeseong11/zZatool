package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Test;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
    Page<Test> findByTestStatusOrderByTestDateDesc(Integer testStatus, Pageable pageable);
}
