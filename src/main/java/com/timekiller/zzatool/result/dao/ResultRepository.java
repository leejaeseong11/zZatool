package com.timekiller.zzatool.result.dao;

import com.timekiller.zzatool.result.entity.Result;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    Page<Result> findByOrderByResultDateDesc(Example example, Pageable pageable);
}
