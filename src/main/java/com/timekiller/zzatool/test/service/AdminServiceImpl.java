package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.TestDTO;

public class AdminServiceImpl implements AdminService {
    @Override
    public void modifyTest(Long testId, TestDTO testDTO) {}

    @Override
    public void removeTest(Long testId) {}

    @Override
    public void modifyQuiz(Long quizId, QuizDTO quizDTO) {}

    @Override
    public void removeQuiz(Long quizId) {}
}
