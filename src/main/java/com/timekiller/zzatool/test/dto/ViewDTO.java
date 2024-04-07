package com.timekiller.zzatool.test.dto;

import lombok.Builder;

public record ViewDTO(
        Long viewId, String viewContent, Long quizId, Integer viewNumber, Integer isCorrect) {
    @Builder
    public ViewDTO {}
}
