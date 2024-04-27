package com.timekiller.zzatool.test.entity;

import com.timekiller.zzatool.hashtag.entity.Hashtag;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "test_hashtag")
public class TestHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_hashtag_id")
    private Long testHashtagId;

    @Column(name = "test_id")
    @NotNull
    private Long testId;

    @ManyToOne
    @JoinColumn(name = "tag_content")
    private Hashtag tagContent;
}
