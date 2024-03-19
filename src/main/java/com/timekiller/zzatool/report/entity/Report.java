package com.timekiller.zzatool.report.entity;

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
public class Report{
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq_generator")
    private Long reportId;

    @Column(name= "quiz_id")
    @NotNull
    private Long quizId;

    @Column(name = "report_reason")
    @NotNull
    private String reportReason;

    @Column(name = "report_date")
    @NotNull
    private Timestamp reportDate;
}