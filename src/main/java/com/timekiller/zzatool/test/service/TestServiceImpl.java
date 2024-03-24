package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.TestRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
}
