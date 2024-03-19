package com.timekiller.zzatool.hashtag.entity;

import com.timekiller.zzatool.test.entity.Test;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor  @AllArgsConstructor
@Getter @Builder @Setter
@Entity
@Table(name = "hashtag")
public class Hashtag{
    @Id
    @Column(name = "tag_content")
    @NotNull
    private String tagContent;

}