package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.report.entity.Report;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.Test;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/test/edit/{testId}")
    public String testForm(
            Model model, @PathVariable("testId") Long testId, @ModelAttribute Report report) {
        return "admin/testForm";
    }

    @PatchMapping("/test/edit/{testId}")
    public String modifyTest(
            Model model, @PathVariable("testId") Long testId, @ModelAttribute Test test) {
        return "redirect:/test/edit/" + test.getTestId();
    }

    @DeleteMapping("/test/edit/{testId}")
    public String removeTest(@PathVariable("testId") Long testId) {
        return "redirect:/report";
    }

    @GetMapping("/quiz/edit/{quizId}")
    public String quizForm(
            Model model, @PathVariable("quizId") Long quizId, @ModelAttribute Report report) {
        return "admin/quizForm";
    }

    @PatchMapping("/quiz/edit/{quizId}")
    public String modifyQuiz(
            Model model, @PathVariable("quizId") Long quizId, @ModelAttribute Quiz quiz) {
        return "redirect:/quiz/edit/" + quiz.getQuizId();
    }

    @DeleteMapping("/quiz/edit/{quizId}")
    public String removeQuiz(@PathVariable("quizId") Long quizId) {
        return "redirect:/report";
    }
}
