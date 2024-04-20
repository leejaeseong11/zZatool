package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public record QuizDTO(
        Long quizId,
        Integer quizNo,
        String quizContent,
        String quizImage,
        Long testId,
        List<ViewDTO> viewList) {
    @Builder
    public QuizDTO {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }
    }
}
