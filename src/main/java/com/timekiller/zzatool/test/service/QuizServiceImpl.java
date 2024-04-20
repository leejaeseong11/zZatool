package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.QuizRepository;
import com.timekiller.zzatool.test.dao.ViewRepository;
import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.View;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final ViewRepository viewRepository;

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
        log.info("quizId={}", quizDTO);
        List<View> viewList = new ArrayList<>();

        Quiz saveQuiz =
                quizRepository.save(
                        Quiz.builder()
                                .quizContent(quizDTO.quizContent())
                                .quizImage(quizDTO.quizImage())
                                .quizNo(quizDTO.quizNo())
                                .testId(quizDTO.testId())
                                .viewList(viewList)
                                .build());

        int i = 0;
        for (ViewDTO viewDTO : quizDTO.viewList()) {
            i += 1;
            View insertView =
                    View.builder()
                            .viewContent(viewDTO.getViewContent())
                            .viewNumber(i)
                            .isCorrect(viewDTO.getIsCorrect() != null ? 1 : 0)
                            .quizId(saveQuiz.getQuizId())
                            .build();
            viewRepository.save(insertView);
            viewList.add(insertView);
        }
    }
}
