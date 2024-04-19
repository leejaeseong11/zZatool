package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}
