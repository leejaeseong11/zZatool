package com.timekiller.zzatool.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Quiz")
@DynamicInsert
/* 문제 Entity */
public class Quiz {
    // [PK] 문제 아이디
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column(name = "quiz_no")
    private Integer quizNo;

    // 문제 내용
    @Column(name = "quiz_content")
    @NotNull
    private String quizContent;

    // 문제 이미지
    @Column(name = "quiz_image")
    private String quizImage;

    // [FK] 테스트 아이디
    @Column(name = "test_id")
    @NotNull
    private Long testId;

    // 정답 횟수
    @Column(name = "correct_count")
    @ColumnDefault(value = "0")
    private Long correctCount;

    // 정답률
    @Column(name = "correct_rate")
    @ColumnDefault(value = "0.0")
    private Float correctRate;

    // 보기 목록
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private List<View> viewList;
}
