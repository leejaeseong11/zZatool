package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findAllByTestId(Long testId);

    @Transactional
    void deleteAllByTestId(Long testId);
}
