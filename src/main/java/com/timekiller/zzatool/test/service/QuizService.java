package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.QuizDTO;

public interface QuizService {
    /* 퀴즈 상세 조회 */
    QuizDTO findQuiz(Long quizId);

    /* 퀴즈 추가 */
    void createQuiz(QuizDTO quizDTO) throws Exception;

    /* 퀴즈 수정 */
    void updateQuiz(Long quizId, QuizDTO quizDTO) throws Exception;
}
