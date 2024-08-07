package com.timekiller.zzatool.test.dao;

import static com.timekiller.zzatool.test.entity.QTest.*;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.timekiller.zzatool.hashtag.entity.Hashtag;
import com.timekiller.zzatool.test.entity.Test;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepositoryCustomImpl implements TestRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    private BooleanExpression likeSearchKeyword(String search) {
        if (StringUtils.hasText(search)) {
            return test.testTitle
                    .like("%" + search + "%")
                    .or(
                            test.hashtagList
                                    .any()
                                    .tagContent
                                    .eq(Hashtag.builder().tagContent(search).build()));
        }
        return null;
    }

    private BooleanExpression limitDate(String date) {
        if (date.equals("all")) {
            return null;
        }
        int numberDate = Integer.parseInt(date);
        Date start = new Date(System.currentTimeMillis() - (long) numberDate * 24 * 60 * 60 * 1000);
        Date end = new Date(System.currentTimeMillis());
        return test.testDate.between(start, end);
    }

    private OrderSpecifier orderTest(String sort) {
        if (sort.equals("new")) {
            return test.testDate.desc();
        } else {
            return test.testCount.asc();
        }
    }

    @Override
    public List<Test> findTestList(int page, int size, TestSearchCond testSearchCond) {

        return jpaQueryFactory
                .select(test)
                .from(test)
                .where(
                        test.testStatus.eq(testSearchCond.getTestStatus()),
                        likeSearchKeyword(testSearchCond.getSearch()),
                        limitDate(testSearchCond.getDate()))
                .offset((long) page * size)
                .limit(size)
                .orderBy(orderTest(testSearchCond.getSort()))
                .fetch();
    }

    @Override
    public Long countSearchTest(TestSearchCond testSearchCond) {
        return jpaQueryFactory
                .select(test.count())
                .from(test)
                .where(
                        test.testStatus.eq(testSearchCond.getTestStatus()),
                        likeSearchKeyword(testSearchCond.getSearch()),
                        limitDate(testSearchCond.getDate()))
                .fetchFirst();
    }
}
