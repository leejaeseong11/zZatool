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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq_generator")
    private Long reportId;

    @Column(nullable = false)
    private Long quizId;

    @Column(nullable = false)
    private String reportReason;

    @Column(nullable = false)
    private Timestamp reportDate;
}