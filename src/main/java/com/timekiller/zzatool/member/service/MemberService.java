package com.timekiller.zzatool.member.service;



public interface MemberService {
    public boolean findEmail(String email);

    public void sendEmail(String email);
}
