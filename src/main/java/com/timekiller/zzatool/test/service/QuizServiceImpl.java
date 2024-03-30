package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.exception.FindException;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dao.QuizRepository;
import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());

    private final QuizRepository quizRepository;
    private final AwsS3Service awsS3Service;
    private final String quizImageUploadPath = "/quiz-image";

    @Override
    public List<Quiz> selectAllQuiz(Long testId) throws FindException {
        try {
            return quizRepository.findAllByTestId(testId);
        } catch (Exception e) {
            throw new FindException("문제 조회에 실패했습니다.");
        }
    }

    @Override
    public void createQuiz(QuizCreateDTO quizCreateDTO, MultipartFile quizImage) throws Exception {
        try {
            if (!Objects.isNull(quizImage)) {
                String imageUrl = awsS3Service.uploadImage(quizImage, quizImageUploadPath);
                quizCreateDTO.setQuizImage(imageUrl);
            }

            Quiz quiz = Quiz.builder()
                    .quizNo(quizCreateDTO.getQuizNo())
                    .quizContent(quizCreateDTO.getQuizContent())
                    .quizImage(quizCreateDTO.getQuizImage())
                    .testId(quizCreateDTO.getTestId())
                    .build();

            quizRepository.save(quiz);
        } catch (Exception e) {
            logger.log(Level.INFO, "퀴즈 생성 실패", e);
            throw new Exception("퀴즈 생성 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAllQuiz(Long testId) throws RemoveException, FindException {
        try {
            List<Quiz> quizList = quizRepository.findAllByTestId(testId);
            for (Quiz quiz : quizList) {
                awsS3Service.deleteImage(quiz.getQuizImage(), quizImageUploadPath);
            }
            quizRepository.deleteAllByTestId(testId);
        } catch (Exception e) {
            throw new RemoveException("문제 삭제에 실패했습니다.");
        }
    }

}
