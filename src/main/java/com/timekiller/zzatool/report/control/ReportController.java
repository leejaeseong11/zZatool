package com.timekiller.zzatool.report.control;

import com.timekiller.zzatool.report.dto.ReportDTO;
import com.timekiller.zzatool.report.service.ReportService;
import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.QuizService;
import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final TestService testService;
    private final QuizService quizService;

    @GetMapping("/add")
    public String form(
            Model model,
            @RequestParam(value = "quizId", required = false) Long quizId,
            @RequestParam(value = "testId", required = false) Long testId) {
        String reportType;
        if (quizId != null) {
            reportType = "quiz";
            QuizDTO quizDTO = quizService.findQuiz(quizId);
            TestDTO testDTO = testService.findTest(quizDTO.testId());
            model.addAttribute("quiz", quizDTO);
            model.addAttribute("pageTitle", testDTO.testTitle());
        } else if (testId != null) {
            reportType = "test";
            TestDTO testDTO = testService.findTest(testId);
            model.addAttribute("test", testDTO);
            model.addAttribute("pageTitle", testDTO.testTitle());
        } else {
            return "error/error";
        }
        model.addAttribute("reportType", reportType);
        model.addAttribute("report", ReportDTO.builder().build());

        return "report/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ReportDTO reportDTO) {
        if (reportDTO.quizId() == null) {
            reportService.addReport(
                    ReportDTO.builder()
                            .reportDate(new Timestamp(System.currentTimeMillis()))
                            .reportReason(reportDTO.reportReason())
                            .testId(reportDTO.testId())
                            .quizId(null)
                            .build());
            //            return "redirect:/test/" + reportDTO.testId();
            return "redirect:/";
        } else {
            QuizDTO quizDTO = quizService.findQuiz(reportDTO.quizId());
            reportService.addReport(
                    ReportDTO.builder()
                            .reportDate(new Timestamp(System.currentTimeMillis()))
                            .reportReason(reportDTO.reportReason())
                            .reportId(reportDTO.reportId())
                            .testId(quizDTO.testId())
                            .quizId(reportDTO.quizId())
                            .build());
            //            return "redirect:/test/" + quizDTO.testId();
            return "redirect:/";
        }
    }

    @GetMapping
    public String findAll(Model model) {
        List<ReportDTO> reportList = reportService.findAllReportList();
        model.addAttribute("reports", reportList);
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
