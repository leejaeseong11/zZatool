package com.timekiller.zzatool.hashtag.entity;

import com.timekiller.zzatool.test.entity.Test;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor  @AllArgsConstructor
@Getter @Builder @Setter
@Entity
@Table(name = "hashtag")
public class Hashtag{
    @Id
    private String tagContent;

}