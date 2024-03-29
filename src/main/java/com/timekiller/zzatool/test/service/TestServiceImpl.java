package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dao.TestRepositoryCustom;
import com.timekiller.zzatool.test.dao.TestSearchCond;
import com.timekiller.zzatool.test.dto.HashtagDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;
import com.timekiller.zzatool.test.entity.TestHashtag;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final TestRepositoryCustom testRepositoryCustom;

    @Override
    public Page<TestDTO> findTestList(Pageable pageable, Integer testStatus) {
        List<TestDTO> selectedTestList = new ArrayList<>();

        Page<Test> selectedTestPage =
                testRepository.findByTestStatusOrderByTestDateDesc(1, pageable);

        for (Test selectedTest : selectedTestPage) {
            selectedTestList.add(testEntityToDTO(selectedTest));
        }

        return new PageImpl<>(selectedTestList, pageable, selectedTestPage.getTotalElements());
    }

    @Override
    public List<TestDTO> findSearchTestList(
            int page, int size, Integer testStatus, String search, String sort, String date) {
        List<TestDTO> selectedTestList = new ArrayList<>();

        //        Page<Test> selectedTestPage =
        //                testRepository.findByTestStatusOrderByTestDateDesc(1, pageable);
        TestSearchCond testSearchCond = new TestSearchCond(testStatus, search, sort, date);
        List<Test> selectedTestPage = testRepositoryCustom.findTestList(page, size, testSearchCond);

        for (Test selectedTest : selectedTestPage) {
            selectedTestList.add(testEntityToDTO(selectedTest));
        }

        return selectedTestList;
    }

    private TestDTO testEntityToDTO(Test testEntity) {
        List<HashtagDTO> hashtagDTOList = new ArrayList<>();
        for (TestHashtag hashtag : testEntity.getHashtagList()) {
            hashtagDTOList.add(
                    HashtagDTO.builder()
                            .testHashtagId(hashtag.getTestHashtagId())
                            .testId(hashtag.getTestId())
                            .tagContent(hashtag.getTagContent())
                            .build());
        }
        return TestDTO.builder()
                .testId(testEntity.getTestId())
                .testTitle(testEntity.getTestTitle())
                .memberId(testEntity.getMemberId())
                .testDate(testEntity.getTestDate())
                .testImage(testEntity.getTestImage())
                .testCount(testEntity.getTestCount())
                .testStatus(testEntity.getTestStatus())
                .hashtagList(hashtagDTOList)
                .build();
    }
}
