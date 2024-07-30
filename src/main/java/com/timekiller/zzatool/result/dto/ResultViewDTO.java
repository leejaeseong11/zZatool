package com.timekiller.zzatool.result.dto;

import com.timekiller.zzatool.test.dto.QuizDTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultViewDTO {
    private QuizDTO quiz; // 문제

    private Integer myNo; // 나의 답
    private String myAnswer;

    private Integer correctNo; // 정답
    private String correctAnswer;
}
