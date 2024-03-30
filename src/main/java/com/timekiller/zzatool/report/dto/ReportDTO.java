package com.timekiller.zzatool.report.dto;

import lombok.Builder;

import java.sql.Timestamp;

public record ReportDTO(
        Long reportId, Long quizId, Long testId, String reportReason, Timestamp reportDate) {
    @Builder
    public ReportDTO {}
}
