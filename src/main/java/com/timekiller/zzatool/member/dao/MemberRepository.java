package com.timekiller.zzatool.member.dao;

import com.timekiller.zzatool.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {}
