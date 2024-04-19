package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.service.QuizService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/test/{testId}/quiz/add")
    public String addForm(
            @PathVariable("testId") int testId,
            @RequestParam(value = "viewCount", defaultValue = "1") int viewCount,
            Model model) {
        List<ViewDTO> viewList = new ArrayList<>();
        viewList.add(ViewDTO.builder().isCorrect(1).build());
        for (int i = 1; i < viewCount; i++) {
            viewList.add(ViewDTO.builder().build());
        }
        QuizDTO quizDTO = QuizDTO.builder().viewList(viewList).build();
        log.info("viewList={}", viewList);
        log.info("viewList={}", quizDTO);
        model.addAttribute("quiz", quizDTO);
        return "quiz/form";
    }

    @PostMapping("/test/{testId}/quiz/add")
    public String add(@ModelAttribute QuizDTO quizDTO) {
        log.info("id={}", quizDTO.quizId());
        quizService.addQuiz(QuizDTO.builder().build());
        return "redirect:/";
    }
}
