package com.timekiller.zzatool.hashtag.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(getTagContent(), hashtag.getTagContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTagContent());
    }
}
