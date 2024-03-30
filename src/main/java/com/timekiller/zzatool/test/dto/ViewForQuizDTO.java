package com.timekiller.zzatool.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewForQuizDTO {
    private String viewContent; // 보기 내용
    private Integer viewNumber; // 보기 번호
    private Integer isCorrect; // 정답 여부
}
