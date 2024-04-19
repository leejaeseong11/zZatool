package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.QuizRepository;
import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.View;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    @Override
    public QuizDTO findQuiz(Long quizId) {
        Quiz selectedQuiz =
                quizRepository
                        .findById(quizId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));
        List<ViewDTO> selectedViewDTO = new ArrayList<>();
        for (View view : selectedQuiz.getViewList()) {
            selectedViewDTO.add(
                    ViewDTO.builder()
                            .viewId(view.getViewId())
                            .viewContent(view.getViewContent())
                            .quizId(view.getQuizId())
                            .viewNumber(view.getViewNumber())
                            .isCorrect(view.getIsCorrect())
                            .build());
        }

        return QuizDTO.builder()
                .quizId(selectedQuiz.getQuizId())
                .quizNo(selectedQuiz.getQuizNo())
                .quizContent(selectedQuiz.getQuizContent())
                .quizImage(selectedQuiz.getQuizImage())
                .testId(selectedQuiz.getTestId())
                .viewList(selectedViewDTO)
                .build();
    }

    @Override
    public void addQuiz(QuizDTO quizDTO) {
        quizRepository.save(
                Quiz.builder()
                        .quizContent(quizDTO.quizContent())
                        .quizImage(quizDTO.quizImage())
                        .quizNo(quizDTO.quizNo())
                        .testId(quizDTO.testId())
                        .build());
    }
}
