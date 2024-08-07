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
            @RequestParam("viewCount") int viewCount,
            Model model) {
        List<ViewDTO> viewList = new ArrayList<>();
        for (int i = 0; i < viewCount; i++) {
            if (i == 0) {
                viewList.add(ViewDTO.builder().isCorrect(1).build());
            } else {
                viewList.add(ViewDTO.builder().build());
            }
        }
        QuizDTO quizDTO = QuizDTO.builder().viewList(viewList).build();
        model.addAttribute("quiz", quizDTO);
        model.addAttribute("testId", testId);
        return "quiz/form";
    }

    @PostMapping("/test/{testId}/quiz/add")
    public String add(@PathVariable("testId") int testId, @ModelAttribute QuizDTO quizDTO)
            throws Exception {
        log.info("quizDTO={}", quizDTO);
        quizService.createQuiz(quizDTO);
        return "redirect:/test/" + testId + "/quiz/add?viewCount=" + quizDTO.viewList().size();
    }

    @GetMapping("/quiz/{quizId}/edit")
    public String editForm(@PathVariable("quizId") Long quizId, Model model) {
        QuizDTO findQuiz = quizService.findQuiz(quizId);
        model.addAttribute("quiz", findQuiz);

        return "quiz/editForm";
    }

    @PostMapping("/quiz/{quizId}/edit")
    public String edit(@ModelAttribute QuizDTO quizDTO, @PathVariable("quizId") Long quizId)
            throws Exception {
        log.info("viewlist={}", quizDTO.viewList());
        quizService.updateQuiz(quizId, quizDTO);
        return "redirect:/test/" + quizDTO.testId();
    }
}
