package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.CommentRepository;
import com.timekiller.zzatool.test.dto.CommentDTO;
import com.timekiller.zzatool.test.entity.Comment;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    @Autowired private CommentRepository commentRepository;

    @Override
    public void addComment(CommentDTO commentDTO) {
        Comment comment =
                Comment.builder()
                        .testId(commentDTO.testId())
                        .commentWriter(commentDTO.commentWriter())
                        .commentContent(commentDTO.commentContent())
                        .commentDate(commentDTO.commentDate())
                        .build();
        commentRepository.save(comment);
    }
}
