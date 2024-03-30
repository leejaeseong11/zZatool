package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.exception.FindException;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuizService {

    /**
     * 테스트 아이디에 해당하는 문제를 모두 조회한다.
     *
     * @param testId 테스트 아이디
     * @return 문제 목록
     * @throws FindException
     */
    List<Quiz> selectAllQuiz(Long testId) throws FindException;

    /**
     * 사용자가 문제를 생성한다.
     *
     * @param quizCreateDTO 문제
     * @param quizImage     문제 이미지
     * @throws Exception
     */
    void createQuiz(QuizCreateDTO quizCreateDTO, MultipartFile quizImage) throws Exception;

    /**
     * 테스트 아이디에 해당하는 문제를 모두 삭제한다.
     *
     * @param testId 테스트 아이디
     * @throws RemoveException
     */
    void deleteAllQuiz(Long testId) throws RemoveException, FindException;
}
