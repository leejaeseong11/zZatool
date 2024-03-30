package com.timekiller.zzatool.result.service;

import com.timekiller.zzatool.result.dao.ResultRepository;
import com.timekiller.zzatool.result.dto.ResultDTO;
import com.timekiller.zzatool.result.entity.Result;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.service.TestServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ResultServiceImpl implements ResultService {
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    @Autowired private ResultRepository resultRepository;
    @Autowired private TestRepository testRepository;

    /* SELECT : 회원이 푼 테스트 목록 조회 */
    @Override
    public Page<ResultDTO> findResultListByMemberId(Long memberId, Pageable pageable) {
        Result exampleResult = Result.builder().memberId(memberId).build();
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        Example<Result> example = Example.of(exampleResult, exampleMatcher);
        Page<Result> resultPageList =
                resultRepository.findByOrderByResultDateDesc(example, pageable);

        List<ResultDTO> resultDTOList = new ArrayList<>();
        for (Result result : resultPageList) {
            String testTitle = findTestTitle(result.getTestId());
            resultDTOList.add(resultEntityToDTO(result, testTitle));
        }

        return new PageImpl<>(resultDTOList, pageable, resultPageList.getTotalElements());
    }

    /* method : 결과 entity를 dto로 변환 */
    private ResultDTO resultEntityToDTO(Result result, String testTitle) {
        return ResultDTO.builder()
                .resultId(result.getResultId())
                .testId(result.getTestId())
                .testTitle(testTitle)
                .resultScore(result.getResultScore())
                .resultDate(result.getResultDate())
                .build();
    }

    /* method : testId에 해당하는 testTitle 리턴 */
    private String findTestTitle(Long testId) {
        return testRepository.findById(testId).get().getTestTitle();
    }
}
