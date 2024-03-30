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
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Test")
@DynamicInsert
/* 테스트 Entity */
public class Test {
    // [PK] 테스트 아이디
    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    // 테스트 제목
    @Column(name = "test_title")
    @NotNull
    private String testTitle;

    // [FK] 회원 아이디
    @Column(name = "member_id")
    @NotNull
    private Long memberId;

    // 테스트 제작일
    @Column(name = "test_date")
    //    @ColumnDefault(value = "sysdate()")
    private Date testDate;

    // 테스트 이미지
    @Column(name = "test_image")
    private String testImage;

    // 테스트 제출 횟수
    @Column(name = "test_count")
    @ColumnDefault(value = "0")
    private Long testCount;

    // 테스트 상태값
    @Column(name = "test_status")
    @ColumnDefault(value = "0")
    private Integer testStatus;

    // 한줄평 목록
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "test_id")
    private List<Comment> commentList;

    // 문제 목록
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "test_id")
    private List<Quiz> quizList;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    private List<TestHashtag> hashtagList;
}
