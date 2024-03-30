package com.timekiller.zzatool.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizWithViewDTO {
    private Long quizId;
    private Integer quizNo;
    private Long testId;
    private String quizContent;
    private String quizImage;
    private List<ViewForQuizDTO> views;
}
