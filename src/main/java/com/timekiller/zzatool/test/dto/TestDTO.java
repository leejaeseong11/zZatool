package com.timekiller.zzatool.test.dto;

import lombok.Builder;

import org.springframework.web.multipart.MultipartFile;

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
        MultipartFile testImageFile,
        String hashtagString,
        List<HashtagDTO> hashtagList,
        List<CommentDTO> commentList,
        List<QuizDTO> quizList) {
    @Builder
    public TestDTO {}
}
