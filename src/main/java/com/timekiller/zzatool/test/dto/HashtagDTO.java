package com.timekiller.zzatool.test.dto;

import lombok.Builder;

public record HashtagDTO(Long testHashtagId, Long testId, String tagContent) {
    @Builder
    public HashtagDTO {}
}
