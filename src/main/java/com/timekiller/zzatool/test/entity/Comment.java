package com.timekiller.zzatool.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Comment")
@DynamicInsert
/* 댓글 Entity */
public class Comment {
    // [PK] 댓글 아이디
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    // [FK] 테스트 아이디
    @Column(name = "test_id")
    @NotNull
    private Long testId;

    // 댓글 내용
    @Column(name = "comment_content")
    @NotNull
    private String commentContent;

    // 작성자 닉네임
    @Column(name = "comment_writer")
    //    @ColumnDefault(value = "익명")
    private String commentWriter;

    // 댓글 작성일
    @Column(name = "comment_date")
    //    @ColumnDefault(value = "sysdate()")
    private Date commentDate;
}
