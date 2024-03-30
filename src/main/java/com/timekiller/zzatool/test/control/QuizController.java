package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.exception.FindException;
import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/quiz/{testId}")
    public ResponseEntity<?> selectAllQuiz(@PathVariable Long testId) {
        try {
            List<Quiz> quizList = quizService.selectAllQuiz(testId);
            List<QuizCreateDTO> quizCreateDTOList = new ArrayList<>();
            for (Quiz quiz : quizList) {
                QuizCreateDTO quizCreateDTO = new QuizCreateDTO();
                quizCreateDTO.setQuizId(quiz.getQuizId());
                quizCreateDTO.setQuizNo(quiz.getQuizNo());
                quizCreateDTO.setTestId(quiz.getTestId());
                quizCreateDTO.setQuizImage(quiz.getQuizImage());
                quizCreateDTO.setQuizContent(quiz.getQuizContent());
                quizCreateDTOList.add(quizCreateDTO);
            }
            return ResponseEntity.ok().body(quizCreateDTOList);
        } catch (FindException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("퀴즈 조회에 실패하였습니다: " + e.getMessage());
        }
    }

    @PostMapping("/quiz")
    public ResponseEntity<?> createQuiz(@RequestPart(name = "quizCreateDTO") QuizCreateDTO quizCreateDTO,
                                        @RequestPart(name = "quizImage", required = false) MultipartFile quizImage) {
        try {
            quizService.createQuiz(quizCreateDTO, quizImage);
            return ResponseEntity.ok().body("퀴즈 생성이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("퀴즈 생성에 실패했습니다.");
        }
    }
}
