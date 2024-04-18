package com.timekiller.zzatool.result.entity;

import com.timekiller.zzatool.test.entity.View;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "result_view")
public class ResultView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_view_id")
    private Long resultViewId;

    // [FK] 결과 아이디
    @Column(name = "result_id")
    @NotNull
    private Long resultId;

    // 나의 답 번호
    @Column(name = "my_no")
    private Integer myNo;

    // 나의 답 내용
    @Column(name = "my_answer")
    private String myAnswer;

    // 정답
    @ManyToOne
    @JoinColumn(name = "view_id")
    @NotNull
    private View correctAnswer;
}
