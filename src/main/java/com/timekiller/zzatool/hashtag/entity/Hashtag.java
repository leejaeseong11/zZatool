package com.timekiller.zzatool.hashtag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @Column(name = "tag_content")
    @NotNull
    private String tagContent;
}
