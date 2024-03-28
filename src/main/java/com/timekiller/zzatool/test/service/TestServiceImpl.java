package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;

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

    @Override
    public Page<TestDTO> findTestList(Integer testStatus, Pageable pageable) {
        List<TestDTO> selectedTestList = new ArrayList<>();
        Page<Test> selectedTestPage =
                testRepository.findByTestStatusOrderByTestDateDesc(1, pageable);

        for (Test selectedTest : selectedTestPage) {
            selectedTestList.add(testEntityToDTO(selectedTest));
        }

        return new PageImpl<>(selectedTestList, pageable, selectedTestPage.getTotalElements());
    }

    @Override
    public void modifyTestByAdmin(Integer testId, TestDTO testDTO) {}

    @Override
    public void removeTestByAdmin(Integer testId) {}

    private TestDTO testEntityToDTO(Test testEntity) {
        return TestDTO.builder()
                .testId(testEntity.getTestId())
                .testTitle(testEntity.getTestTitle())
                .memberId(testEntity.getMemberId())
                .testDate(testEntity.getTestDate())
                .testImage(testEntity.getTestImage())
                .testCount(testEntity.getTestCount())
                .testStatus(testEntity.getTestStatus())
                .build();
    }
}
