package com.timekiller.zzatool.report.control;

import com.timekiller.zzatool.report.entity.Report;
import com.timekiller.zzatool.report.service.ReportService;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/add")
    public String reportForm(
            Model model,
            @RequestParam(value = "quiz_id", required = false) Long quizId,
            @RequestParam(value = "test_id", required = false) Long testId) {
        return "report/form";
    }

    @PostMapping("/add")
    public String addReport(@ModelAttribute Report report, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        if (referer != null) {
            return "redirect:" + referer;
        } else {
            return "redirect:/";
        }
    }

    @GetMapping
    public String findAllReport(Model model) {
        return "report/reports";
    }

    @GetMapping("/{reportId}")
    public String findReport(Model model, @RequestParam("reportId") Long reportId) {
        return "report/report";
    }

    @PatchMapping("/{reportId}")
    public String modifyReport(@RequestParam("reportId") Long reportId) {
        return "redirect:/report";
    }
}
