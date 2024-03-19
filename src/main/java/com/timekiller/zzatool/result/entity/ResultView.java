package com.timekiller.zzatool.result.entity;

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
@Table(name="result_view")
public class ResultView{
    // [FK] 결과 아이디

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_view_id")
    private Long resultViewId;

    @Column(name = "result_id")
    @NotNull
    private Long resultId;

    // [FK] 보기 아이디
    @Column(name = "view_id")
    @NotNull
    private Long viewId;
}
