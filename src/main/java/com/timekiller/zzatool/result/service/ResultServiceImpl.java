package com.timekiller.zzatool.result.service;

import com.timekiller.zzatool.result.dao.ResultRepository;
import com.timekiller.zzatool.result.dto.ResultDTO;
import com.timekiller.zzatool.result.dto.ResultViewDTO;
import com.timekiller.zzatool.result.entity.Result;
import com.timekiller.zzatool.result.entity.ResultView;
import com.timekiller.zzatool.test.dao.QuizRepository;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dao.ViewRepository;
import com.timekiller.zzatool.test.dto.QuizDTO;
import com.timekiller.zzatool.test.entity.Quiz;
import com.timekiller.zzatool.test.entity.View;
import com.timekiller.zzatool.test.service.TestServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ResultServiceImpl implements ResultService {
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    @Autowired private ResultRepository resultRepository;
    @Autowired private TestRepository testRepository;
    @Autowired private ViewRepository viewRepository;
    @Autowired private QuizRepository quizRepository;

    /* SELECT : 회원이 푼 테스트 목록 조회 */
    @Override
    public Page<ResultDTO> findResultListByMemberId(Long memberId, Pageable pageable) {
        List<Result> resultList =
                resultRepository.findByMemberIdOrderByResultDateDesc(memberId, pageable);

        List<ResultDTO> resultDTOList = new ArrayList<>();
        for (Result result : resultList) {
            String testTitle = findTestTitle(result.getTestId());
            resultDTOList.add(resultEntityToDTO(result, testTitle));
        }

        Result exampleResult = Result.builder().memberId(memberId).build();
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        Example<Result> example = Example.of(exampleResult, exampleMatcher);
        Long cnt = resultRepository.count(example);

        return new PageImpl<>(resultDTOList, pageable, cnt);
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
        try {
            return testRepository.findById(testId).get().getTestTitle();
        } catch (Exception e) {
            return "존재하지 않는 테스트입니다";
        }
    }

    /* SELECT : 결과 상세 조회 */
    @Override
    public ResultDTO findResultByResultId(Long resultId) throws Exception {
        Optional<Result> resultOpt = resultRepository.findById(resultId);
        if (!resultOpt.isPresent()) {
            throw new Exception("결과가 존재하지 않습니다");
        }

        Result result = resultOpt.get();
        List<ResultViewDTO> resultViewDTOList = findResultViewList(result.getResultViewList());
        ResultDTO resultDTO =
                ResultDTO.builder()
                        .resultId(result.getResultId())
                        .testId(result.getTestId())
                        .resultScore(result.getResultScore())
                        .resultViewList(resultViewDTOList)
                        .build();
        return resultDTO;
    }

    /* method : resultView 목록 생성하기 */
    private List<ResultViewDTO> findResultViewList(List<ResultView> resultViewList)
            throws Exception {
        List<ResultViewDTO> resultViewDTOList = new ArrayList<>();
        for (ResultView resultView : resultViewList) {
            ResultViewDTO resultViewDTO =
                    ResultViewDTO.builder()
                            .quiz(findQuizByViewId(resultView.getCorrectAnswer().getViewId()))
                            .myNo(resultView.getMyNo())
                            .myAnswer(resultView.getMyAnswer())
                            .correctNo(resultView.getCorrectAnswer().getViewNumber())
                            .correctAnswer(resultView.getCorrectAnswer().getViewContent())
                            .build();
            resultViewDTOList.add(resultViewDTO);
        }
        return resultViewDTOList;
    }

    /* method : viewId에 해당하는 quiz 리턴 */
    private QuizDTO findQuizByViewId(Long viewId) throws Exception {
        Optional<View> viewOpt = viewRepository.findById(viewId);
        if (!viewOpt.isPresent()) {
            throw new Exception("보기가 존재하지 않습니다");
        }

        View view = viewOpt.get();

        Optional<Quiz> quizOpt = quizRepository.findById(view.getQuizId());
        if (!quizOpt.isPresent()) {
            throw new Exception("문제가 존재하지 않습니다");
        }

        Quiz quiz = quizOpt.get();
        QuizDTO quizDTO =
                QuizDTO.builder()
                        .quizNo(quiz.getQuizNo())
                        .quizImage(quiz.getQuizImage())
                        .quizContent(quiz.getQuizContent())
                        .build();

        return quizDTO;
    }
}
