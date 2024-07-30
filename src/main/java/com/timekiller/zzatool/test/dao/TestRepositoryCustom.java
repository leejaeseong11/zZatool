package com.timekiller.zzatool.test.dao;

import com.timekiller.zzatool.test.entity.Test;

import java.util.List;

public interface TestRepositoryCustom {
    List<Test> findTestList(int page, int size, TestSearchCond testSearchCond);

    Long countSearchTest(TestSearchCond testSearchCond);
}
