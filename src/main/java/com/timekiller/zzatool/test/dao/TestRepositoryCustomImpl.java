package com.timekiller.zzatool.test.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestRepositoryCustomImpl implements TestRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
}
