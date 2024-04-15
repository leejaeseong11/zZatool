package com.timekiller.zzatool.member.dao;

import com.timekiller.zzatool.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByEmailAndMemberStatus(String email, Integer memberStatus);

    public Member findByNicknameAndMemberStatus(String nickname, Integer memberStatus);
}
