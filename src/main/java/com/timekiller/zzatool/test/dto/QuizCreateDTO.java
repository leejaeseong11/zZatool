package com.timekiller.zzatool.test.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizCreateDTO {
    private Long quizId; // [PK] 문제 아이디
    private Integer quizNo; // 문제 번호
    private String quizContent; // 문제 내용
    private String quizImage; // 문제 이미지
    private Long testId; // [FK] 테스트 아이디
}
