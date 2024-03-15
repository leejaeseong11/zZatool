package com.timekiller.zzatool.result.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ResultViewKey implements Serializable {
    // [FK] 결과 아이디
    @Column(name = "result_id")
    @NotNull
    private Long resultId;

    // [FK] 보기 아이디
    @Column(name = "view_id")
    @NotNull
    private Long viewId;
}
