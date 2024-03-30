package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.TestDTO;

public interface AdminService {

    /* 관리자의 테스트 수정 */
    void modifyTest(Long testId, TestDTO testDTO);

    /* 관리자의 테스트 삭제 */
    void removeTest(Long testId);

    /* 관리자의 퀴즈 수정 */
    void modifyQuiz(Long quizId, QuizDTO quizDTO);

    /* 관리자의 퀴즈 삭제 */
    void removeQuiz(Long quizId);
}
