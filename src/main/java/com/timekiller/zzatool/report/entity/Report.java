package com.timekiller.zzatool.report.entity;

import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.Test;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "report")
public class Report {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @Column(name = "report_reason")
    @NotNull
    private String reportReason;

    @Column(name = "report_date")
    @NotNull
    private Timestamp reportDate;

    @Column(name = "report_status")
    private Integer reportStatus;
}
