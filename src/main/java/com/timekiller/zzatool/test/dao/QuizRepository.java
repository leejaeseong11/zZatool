package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT q FROM Quiz q WHERE q.testId = :testId")
    List<Quiz> findByTestId(@Param("testId") Long testId);
}
