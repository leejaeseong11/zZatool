package com.timekiller.zzatool.testhashtag.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "test_hashtag")
public class TestHashtag{
    @EmbeddedId
    private TestHashtagKey id = new TestHashtagKey();

}