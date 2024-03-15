package com.timekiller.zzatool.result.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "ResultView")
@DynamicInsert
/* 결과 보기 Entity */
public class ResultView {
    @EmbeddedId private ResultViewKey id = new ResultViewKey();
}
