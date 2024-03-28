package com.timekiller.zzatool.report.dao;

import com.timekiller.zzatool.report.entity.Report;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {}
