package com.timekiller.zzatool.test.dao;

import lombok.Data;

import org.springframework.util.StringUtils;

@Data
public class TestSearchCond {
    private Integer testStatus;
    private String search;
    private String sort;
    private String date;

    public TestSearchCond(Integer testStatus, String search, String sort, String date) {
        this.testStatus = testStatus;
        this.search = search;
        if (!StringUtils.hasText(sort)) {
            this.sort = "new";
        } else {
            this.sort = sort;
        }

        if (!StringUtils.hasText(date)) {
            this.date = "all";
        } else {
            this.date = date;
        }
    }
}
