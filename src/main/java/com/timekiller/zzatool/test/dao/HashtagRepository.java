package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.TestHashtag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<TestHashtag, Long> {}
