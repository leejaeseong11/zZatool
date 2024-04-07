package com.timekiller.zzatool.member.service;

import com.timekiller.zzatool.member.dao.MemberRepository;
import com.timekiller.zzatool.member.dto.MemberDTO;
import com.timekiller.zzatool.member.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberRepository mr;

    @Autowired
    public MemberServiceImpl(MemberRepository mr) {
        this.mr = mr;
    }

    private MemberDTO toDTO(Member entity){
        return MemberDTO.builder()
                .memberId(entity.getMemberId())
                .email(entity.getEmail())
                .nickname(entity.getNickname())
                .password(entity.getPassword())
                .role(entity.getRole())
                .memberStatus(entity.getMemberStatus())
                .build();
    }

    private Member toDTO(MemberDTO dto){
        return Member.builder()
                .memberId(dto.getMemberId())
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .role(dto.getRole())
                .memberStatus(dto.getMemberStatus())
                .build();
    }

    public boolean findEmail(String email){
        Member member = mr.findByEmailAndMemberStatus(email, 1);

        if(member==null){
            return false;
        }else{
            return true;
        }
    }

    public void sendEmail(String email){}
}
