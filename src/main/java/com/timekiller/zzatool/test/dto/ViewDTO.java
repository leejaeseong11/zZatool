package com.timekiller.zzatool.test.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewDTO {
    private Long viewId;
    private String viewContent;
    private Long quizId;
    private Integer viewNumber;
    private Integer isCorrect;
}
