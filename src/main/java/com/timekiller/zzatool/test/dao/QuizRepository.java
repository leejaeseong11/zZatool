package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
