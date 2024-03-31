package com.timekiller.zzatool.member.dto;

import com.timekiller.zzatool.member.entity.Role;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
public class MemberDTO {
    private Long memberId;
    private String email;
    private String nickname;
    private String password;
    private Role role;
    Integer memberStatus;

}
