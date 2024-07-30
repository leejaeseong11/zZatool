package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import java.util.Date;

public record CommentDTO(
        Long commentId,
        Long testId,
        String commentContent,
        String commentWriter,
        Date commentDate) {
    @Builder
    public CommentDTO {}
}
