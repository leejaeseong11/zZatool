package com.timekiller.zzatool.report.service;

import com.timekiller.zzatool.report.dto.ReportDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    @Override
    public void addReport() {}

    @Override
    public List<ReportDTO> findAllReportList() {
        return null;
    }

    @Override
    public ReportDTO findReport() {
        return null;
    }

    @Override
    public void handleReport(int reportId) {}
}
