package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import org.springframework.web.multipart.MultipartFile;

public interface QuizService {

    /**
     * 사용자가 문제를 생성한다.
     *
     * @param quiz      문제
     * @param quizImage 문제 이미지
     * @throws Exception
     */
    void createQuiz(QuizCreateDTO quizCreateDTO, MultipartFile quizImage) throws Exception;
}
