package com.timekiller.zzatool.testhashtag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Embeddable
public class TestHashtagKey implements Serializable {
    @Column(name = "test_id")
    @NotNull
    private Long testId;

    @Column(name = "tag_content")
    @NotNull
    private String tag_content;
}