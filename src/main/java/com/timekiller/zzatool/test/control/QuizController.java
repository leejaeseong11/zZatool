package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.exception.FindException;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import com.timekiller.zzatool.test.dto.QuizWithViewDTO;
import com.timekiller.zzatool.test.dto.ViewForQuizDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.View;
import com.timekiller.zzatool.test.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
            List<QuizWithViewDTO> quizWithViewDTOList = new ArrayList<>();

            for (Quiz quiz : quizList) {
                QuizWithViewDTO quizWithViewDTO = new QuizWithViewDTO();
                quizWithViewDTO.setQuizId(quiz.getQuizId());
                quizWithViewDTO.setQuizNo(quiz.getQuizNo());
                quizWithViewDTO.setTestId(quiz.getTestId());
                quizWithViewDTO.setQuizContent(quiz.getQuizContent());
                quizWithViewDTO.setQuizImage(quiz.getQuizImage());

                // 보기 정보 추가
                List<View> viewList = quiz.getViewList();
                List<ViewForQuizDTO> viewForQuizDTOList = new ArrayList<>();
                for (View view : viewList) {
                    ViewForQuizDTO viewForQuizDTO = new ViewForQuizDTO();
                    viewForQuizDTO.setViewContent(view.getViewContent());
                    viewForQuizDTO.setViewNumber(view.getViewNumber());
                    viewForQuizDTO.setIsCorrect(view.getIsCorrect());
                    viewForQuizDTOList.add(viewForQuizDTO);
                }
                quizWithViewDTO.setViews(viewForQuizDTOList);

                quizWithViewDTOList.add(quizWithViewDTO);
            }
            return ResponseEntity.ok().body(quizWithViewDTOList);
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

    @DeleteMapping("quiz/{testId}")
    public ResponseEntity<?> deleteAllQuiz(@PathVariable Long testId) {
        try {
            quizService.deleteAllQuiz(testId);
            return ResponseEntity.ok().body("문제 삭제가 완료되었습니다.");
        } catch (FindException e) {
            return ResponseEntity.badRequest().body("문제 조회에 실패했습니다.");
        } catch (RemoveException e) {
            return ResponseEntity.badRequest().body("문제 삭제에 실패했습니다.");
        }
    }
}

