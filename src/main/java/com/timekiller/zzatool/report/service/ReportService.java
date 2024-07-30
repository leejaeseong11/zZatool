package com.timekiller.zzatool.report.service;

import com.timekiller.zzatool.report.dto.ReportDTO;

import java.util.List;

public interface ReportService {
    /* 회원이 퀴즈를 신고 */
    void addReport(ReportDTO reportDTO);

    /* 관리자가 신고목록 조회 */
    List<ReportDTO> findAllReportList();

    /* 신고목록 상세 조회 */
    ReportDTO findReport();

    /* 관리자가 신고 처리 */
    void handleReport(int reportId);
}
