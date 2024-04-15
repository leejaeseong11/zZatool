package com.timekiller.zzatool.member.service;



public interface MemberService {
    public boolean findEmail(String email);

    public boolean findNickname(String nickname);

    public void sendEmail(String email);
}
