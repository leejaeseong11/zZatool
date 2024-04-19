package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dto.CommentDTO;

public interface CommentService {
    /**
     * 한줄평을 추가한다
     *
     * @param commentDTO 한줄평 정보
     */
    void addComment(CommentDTO commentDTO);
}
