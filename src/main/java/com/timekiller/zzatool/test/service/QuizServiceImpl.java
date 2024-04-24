package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
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

    private final AwsS3Service awsS3Service;
    private final String profileImageUploadPath = "/quiz-image";

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
    public void createQuiz(QuizDTO quizDTO) throws Exception {
        List<View> viewList = new ArrayList<>();
        Integer quizNo = quizRepository.countByTestId(quizDTO.testId()) + 1;

        String quizImage = "";
        if (!quizDTO.quizImageFile().isEmpty()) {
            try {
                quizImage =
                        awsS3Service.uploadImage(quizDTO.quizImageFile(), profileImageUploadPath);
            } catch (Exception e) {
                throw new Exception("퀴즈 생성 실패: " + e.getMessage(), e);
            }
        }

        Quiz saveQuiz =
                quizRepository.save(
                        Quiz.builder()
                                .quizContent(quizDTO.quizContent())
                                .quizImage(quizImage)
                                .quizNo(quizNo)
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

    @Override
    public void updateQuiz(Long quizId, QuizDTO quizDTO) throws Exception {
        List<View> updatedViewList = new ArrayList<>();
        Quiz findQuiz =
                quizRepository
                        .findById(quizId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀴즈입니다."));

        if (!quizDTO.quizImageFile().isEmpty()) {
            try {
                String testImage =
                        awsS3Service.uploadImage(quizDTO.quizImageFile(), profileImageUploadPath);
                findQuiz.modifyQuizImage(testImage);

            } catch (Exception e) {
                throw new Exception("테스트 생성 실패: " + e.getMessage(), e);
            }
        }
        findQuiz.modifyQuizContent(quizDTO.quizContent());

        int i = 0;
        for (ViewDTO viewDTO : quizDTO.viewList()) {
            i += 1;
            View updateView =
                    viewRepository
                            .findById(viewDTO.getViewId())
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 보기입니다."));
            updateView.modifyViewContent(viewDTO.getViewContent());
            updateView.modifyIsCorrect(viewDTO.getIsCorrect());
            viewRepository.save(updateView);
            updatedViewList.add(updateView);
        }

        findQuiz.modifyViewList(updatedViewList);
        quizRepository.save(findQuiz);
    }
}
