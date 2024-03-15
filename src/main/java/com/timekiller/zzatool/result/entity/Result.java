package com.timekiller.zzatool.result.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Result")
@DynamicInsert
@SequenceGenerator(
        name = "result_no_seq_generator",
        sequenceName = "result_no_seq",
        initialValue = 1,
        allocationSize = 1)
/* 결과 Entity */
public class Result {
    // [PK] 결과 아이디
    @Id
    @Column(name = "result_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "result_no_seq_generator")
    private Long resultId;

    // 결과 점수
    @Column(name = "result_score")
    @NotNull
    private Integer resultScore;

    // [FK] 회원 아이디
    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    // [FK] 테스트 아이디
    @Column(name = "test_id")
    @NotNull
    private Long testId;

    // 제출일
    @Column(name = "result_date")
    @ColumnDefault(value = "sysdate()")
    private Date resultDate;

    // 결과 보기 목록
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "result_id")
    private List<ResultView> resultViewList;
}
