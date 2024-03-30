package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.QuizCreateDTO;
import com.timekiller.zzatool.test.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;

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
