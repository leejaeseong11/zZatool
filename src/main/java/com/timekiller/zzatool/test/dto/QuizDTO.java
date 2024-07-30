package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public record QuizDTO(
        Long quizId,
        Integer quizNo,
        String quizContent,
        String quizImage,
        Long testId,
        MultipartFile quizImageFile,
        List<ViewDTO> viewList) {
    @Builder
    public QuizDTO {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }
    }
}
