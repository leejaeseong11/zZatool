package com.timekiller.zzatool.report.service;

import com.timekiller.zzatool.report.dao.ReportRepository;
import com.timekiller.zzatool.report.dto.ReportDTO;
import com.timekiller.zzatool.report.entity.Report;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.Test;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    @Override
    public void addReport(ReportDTO reportDTO) {
        reportRepository.save(reportDTOtoEntity(reportDTO));
    }

    @Override
    public List<ReportDTO> findAllReportList() {
        List<ReportDTO> selectedReportList = new ArrayList<>();

        List<Report> reportList = reportRepository.findAllByReportStatus(0);
        for (Report report : reportList) {
            selectedReportList.add(reportEntityToDTO(report));
        }
        return selectedReportList;
    }

    @Override
    public ReportDTO findReport() {

        return null;
    }

    private ReportDTO reportEntityToDTO(Report report) {
        Quiz quiz = report.getQuiz();
        Long quizId = quiz == null ? null : quiz.getQuizId();
        return ReportDTO.builder()
                .reportId(report.getReportId())
                .quizId(quizId)
                .testId(report.getTest().getTestId())
                .reportReason(report.getReportReason())
                .reportDate(report.getReportDate())
                .build();
    }

    @Override
    public void handleReport(int reportId) {}

    private Report reportDTOtoEntity(ReportDTO reportDTO) {
        Quiz quiz = null;
        if (reportDTO.quizId() != null) {
            quiz = Quiz.builder().quizId(reportDTO.quizId()).build();
        }
        Test test = Test.builder().testId(reportDTO.testId()).build();
        return Report.builder()
                .quiz(quiz)
                .test(test)
                .reportReason(reportDTO.reportReason())
                .reportDate(reportDTO.reportDate())
                .reportStatus(0)
                .build();
    }
}
