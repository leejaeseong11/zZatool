package com.timekiller.zzatool.result.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultViewDTO {
    private Long quizNo; // 문제 번호
    private boolean isCorrect; // 정답 여부
    private Integer myAnswer; // 나의 답
    private Integer correctAnswer; // 정답
}
