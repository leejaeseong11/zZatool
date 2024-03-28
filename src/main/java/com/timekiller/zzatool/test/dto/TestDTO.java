package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import java.util.Date;

public record TestDTO(
        Long testId,
        String testTitle,
        Long memberId,
        Date testDate,
        String testImage,
        Long testCount,
        Integer testStatus) {
    @Builder
    public TestDTO {}
}
