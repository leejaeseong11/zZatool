package com.timekiller.zzatool.report.dao;

import com.timekiller.zzatool.report.entity.Report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByReportStatus(Integer reportStatus);
}
