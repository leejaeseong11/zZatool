package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.TestDTO;

public class AdminServiceImpl implements AdminService {
    @Override
    public void modifyTest(Integer testId, TestDTO testDTO) {}

    @Override
    public void removeTest(Integer testId) {}

    @Override
    public void modifyQuiz(Integer quizId, QuizDTO quizDTO) {}

    @Override
    public void removeQuiz(Integer quizId) {}
}