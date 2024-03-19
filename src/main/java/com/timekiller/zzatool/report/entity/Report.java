package com.timekiller.zzatool.report.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "report")
public class Report{
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq_generator")
    private Long reportId;

    @Column(name= "quiz_id",nullable = false)
    private Long quizId;

    @Column(name = "report_reason",nullable = false)
    private String reportReason;

    @Column(name = "report_date",nullable = false)
    private Timestamp reportDate;
}