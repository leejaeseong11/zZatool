package com.timekiller.zzatool.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "View")
@DynamicInsert
@SequenceGenerator(
        name = "view_no_seq_generator",
        sequenceName = "view_no_seq",
        initialValue = 1,
        allocationSize = 1)
/* 보기 Entity */
public class View {
    // [PK] 보기 아이디
    @Id
    @Column(name = "view_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "view_no_seq_generator")
    private Long viewId;

    // 보기 내용
    @Column(name = "view_content")
    @NotNull
    private String viewContent;

    // [FK] 문제 아이디
    @Column(name = "quiz_id")
    @NotNull
    private Long quizId;

    // 보기 번호
    @Column(name = "view_number")
    private Integer viewNumber;

    // 정답 여부
    @Column(name = "is_correct")
    private Integer isCorrect;
}
