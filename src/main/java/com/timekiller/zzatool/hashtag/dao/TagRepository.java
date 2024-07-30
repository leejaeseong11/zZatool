package com.timekiller.zzatool.hashtag.dao;

import com.timekiller.zzatool.hashtag.entity.Hashtag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Hashtag, Long> {}
