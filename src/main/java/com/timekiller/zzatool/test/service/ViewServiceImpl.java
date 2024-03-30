package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.exception.AddException;
import com.timekiller.zzatool.test.dao.ViewRepository;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.View;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    private final ViewRepository viewRepository;

    @Override
    public void createView(ViewDTO viewDTO) throws AddException {
        try {
            Quiz quiz = Quiz.builder().quizId(viewDTO.getQuizId()).build();

            View view = View.builder()
                    .viewContent(viewDTO.getViewContent())
                    .quiz(quiz)
                    .isCorrect(viewDTO.getIsCorrect())
                    .viewNumber(viewDTO.getViewNumber())
                    .build();

            viewRepository.save(view);
        } catch (Exception e) {
            logger.log(Level.INFO, "보기 생성 실패", e);
            throw new AddException("보기 생성 실패");
        }
    }
}
