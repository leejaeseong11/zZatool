package com.timekiller.zzatool.report.control;

import com.timekiller.zzatool.report.entity.Report;
import com.timekiller.zzatool.report.service.ReportService;
import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.QuizService;
import com.timekiller.zzatool.test.service.TestService;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final TestService testService;
    private final QuizService quizService;

    @GetMapping("/add")
    public String form(
            Model model,
            @RequestParam(value = "quiz_id", required = false) Long quizId,
            @RequestParam(value = "test_id", required = false) Long testId) {
        String reportType;
        if (quizId != null) {
            reportType = "quiz";
            QuizDTO quizDTO = quizService.findQuiz(quizId);
            model.addAttribute("quiz", quizDTO);
        } else if (testId != null) {
            reportType = "test";
            TestDTO testDTO = testService.findTest(testId);
            model.addAttribute("test", testDTO);
        } else {
            return "error/error";
        }
        model.addAttribute("reportType", reportType);

        return "report/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Report report, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping
    public String findAll(Model model) {
        return "report/reports";
    }

    @GetMapping("/{reportId}")
    public String find(Model model, @RequestParam("reportId") Long reportId) {
        return "report/report";
    }

    @PatchMapping("/{reportId}")
    public String modify(@RequestParam("reportId") Long reportId) {
        return "redirect:/report/" + reportId;
    }
}
