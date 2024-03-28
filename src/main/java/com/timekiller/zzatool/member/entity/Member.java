package com.timekiller.zzatool.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "nickname")
    @NotNull
    private String nickname;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "member_status")
    @NotNull
    Integer memberStatus;
}
