package com.timekiller.zzatool.report.entity;

import com.timekiller.zzatool.test.entity.Quiz;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(name= "quiz_id")
    @NotNull
    private Quiz quiz;

    @Column(name = "report_reason")
    @NotNull
    private String reportReason;

    @Column(name = "report_date")
    @NotNull
    private Timestamp reportDate;
}