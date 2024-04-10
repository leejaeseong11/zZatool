package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

public record TestDTO(
        Long testId,
        String testTitle,
        Long memberId,
        String memberName,
        Date testDate,
        String testImage,
        Long testCount,
        Integer testStatus,
        List<HashtagDTO> hashtagList,
        List<CommentDTO> commentList) {
    @Builder
    public TestDTO {}
}
