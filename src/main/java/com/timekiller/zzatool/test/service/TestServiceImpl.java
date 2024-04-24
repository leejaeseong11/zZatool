package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.member.dao.MemberRepository;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dao.TestRepositoryCustom;
import com.timekiller.zzatool.test.dao.TestSearchCond;
import com.timekiller.zzatool.test.dto.*;
import com.timekiller.zzatool.test.entity.*;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    // Logger 인스턴스 생성
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    private final TestRepository testRepository;
    private final MemberRepository memberRepository;
    private final AwsS3Service awsS3Service;
    private final String profileImageUploadPath = "/test-image";
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

    @Override
    public TestDTO findTest(Long testId) {
        return testEntityToDTO(
                testRepository
                        .findById(testId)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 테스트입니다.")));
    }

    @Override
    public Long countSearchTest(String search) {
        return testRepositoryCustom.countSearchTest(search);
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

    @Override
    public Test createTest(TestDTO testDTO) throws Exception {
        String testImage = "";
        if (!testDTO.testImageFile().isEmpty()) {
            try {
                testImage =
                        awsS3Service.uploadImage(testDTO.testImageFile(), profileImageUploadPath);
            } catch (Exception e) {
                throw new Exception("테스트 생성 실패: " + e.getMessage(), e);
            }
        }
        return testRepository.save(
                Test.builder()
                        .testTitle(testDTO.testTitle())
                        .testDate(new Date(System.currentTimeMillis()))
                        .testImage(testImage)
                        .memberId(1L) // todo: change user id
                        .testStatus(1)
                        .build());
    }

    //    @Override
    //    public void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws
    // Exception {
    //        try {
    //            if (!Objects.isNull(testImage)) {
    //                String imageUrl = awsS3Service.uploadImage(testImage, profileImageUploadPath);
    //                testCreateDTO.setTestImage(imageUrl);
    //            }
    //
    //            Test test =
    //                    Test.builder()
    //                            .testTitle(testCreateDTO.getTestTitle())
    //                            .testDate(testCreateDTO.getTestDate())
    //                            .testImage(testCreateDTO.getTestImage())
    //                            .memberId(testCreateDTO.getMemberId())
    //                            .build();
    //
    //            testRepository.save(test);
    //        } catch (Exception e) {
    //            logger.log(Level.INFO, "테스트 생성 실패", e);
    //            throw new Exception("테스트 생성 실패: " + e.getMessage(), e);
    //        }
    //    }

    @Override
    public void deleteTest(Long testId, Long memberId) throws RemoveException {
        Optional<Test> optionalTest = testRepository.findById(testId);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            if (test.getMemberId().equals(memberId)) {
                if (test.getTestCount() == 0L) {
                    testRepository.deleteById(testId);
                    awsS3Service.deleteImage(test.getTestImage(), profileImageUploadPath);
                } else {
                    throw new RemoveException("테스트를 삭제할 수 없습니다.");
                }
            }
        }
    }

    /* SELECT : 회원이 만든 테스트 목록 조회 */
    @Override
    public Page<MyTestDTO> findTestListByMemberId(Long memberId, String order, Pageable pageable)
            throws Exception {
        String[] orderArr = {"date", "count"};
        List<Test> testList = new ArrayList<>();
        if (order.equals(orderArr[0])) testList = orderByTestDate(memberId, pageable);
        else if (order.equals(orderArr[1])) testList = orderByTestCount(memberId, pageable);
        else throw new Exception("잘못된 접근입니다");
        if (testList.size() == 0) throw new Exception("테스트가 존재하지 않습니다");

        List<MyTestDTO> myTestDTOList = new ArrayList<>();
        for (Test test : testList) {
            myTestDTOList.add(myTestEntityToDTO(test));
        }

        Test exampleTest = Test.builder().memberId(memberId).build();
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll();
        Example<Test> example = Example.of(exampleTest, exampleMatcher);
        Long cnt = testRepository.count(example);

        return new PageImpl<>(myTestDTOList, pageable, cnt);
    }

    /* method : 최신순으로 정렬된 테스트 목록 리턴 */
    private List<Test> orderByTestDate(Long memberId, Pageable pageable) throws Exception {
        try {
            return testRepository.findByMemberIdOrderByTestDateDesc(memberId, pageable);
        } catch (Exception e) {
            throw new Exception("테스트를 조회할 수 없습니다");
        }
    }

    /* method : 조회순으로 정렬된 테스트 목록 리턴 */
    private List<Test> orderByTestCount(Long memberId, Pageable pageable) throws Exception {
        try {
            return testRepository.findByMemberIdOrderByTestCountDesc(memberId, pageable);
        } catch (Exception e) {
            throw new Exception("테스트를 조회할 수 없습니다");
        }
    }

    /* method : 테스트 entity를 dto로 변환 */
    private MyTestDTO myTestEntityToDTO(Test test) {
        return MyTestDTO.builder()
                .testId(test.getTestId())
                .testTitle(test.getTestTitle())
                .testDate(test.getTestDate())
                .testCount(test.getTestCount())
                .build();
    }

    /* SELECT : 테스트 정보 조회 */
    @Override
    public TestDTO findTestByTestId(Long testId) throws Exception {
        Optional<Test> testOpt = testRepository.findById(testId);
        if (!testOpt.isPresent()) {
            throw new Exception("테스트가 존재하지 않습니다");
        }

        Test test = testOpt.get();
        if (test.getTestStatus() == 0) {
            throw new Exception("테스트가 존재하지 않습니다");
        }

        List<HashtagDTO> hashtagDTOList = new ArrayList<>();
        for (TestHashtag hashtag : test.getHashtagList()) {
            HashtagDTO hashtagDTO =
                    HashtagDTO.builder()
                            .testHashtagId(hashtag.getTestHashtagId())
                            .tagContent(hashtag.getTagContent())
                            .build();
            hashtagDTOList.add(hashtagDTO);
        }

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : test.getCommentList()) {
            CommentDTO commentDTO =
                    CommentDTO.builder()
                            .commentId(comment.getCommentId())
                            .commentWriter(comment.getCommentWriter())
                            .commentContent(comment.getCommentContent())
                            .commentDate(comment.getCommentDate())
                            .build();
            commentDTOList.add(commentDTO);
        }

        List<QuizDTO> quizDTOList = findQuizList(test);
        String memberName = findNicknameByMemberId(test.getMemberId());
        return testEntityToDTO(test, hashtagDTOList, commentDTOList, quizDTOList, memberName);
    }

    /* method : 회원 아이디로 회원 닉네임 조회 */
    private String findNicknameByMemberId(Long memberId) throws Exception {
        try {
            return memberRepository.findById(memberId).get().getNickname();
        } catch (Exception e) {
            throw new Exception("테스트가 존재하지 않습니다");
        }
    }

    /* method : 테스트 entity를 dto로 변환 */
    private TestDTO testEntityToDTO(
            Test test,
            List<HashtagDTO> hashtagList,
            List<CommentDTO> commentList,
            List<QuizDTO> quizList,
            String memberName) {
        return TestDTO.builder()
                .testId(test.getTestId())
                .testTitle(test.getTestTitle())
                .memberName(memberName)
                .testImage(test.getTestImage())
                .testCount(test.getTestCount())
                .testDate(test.getTestDate())
                .hashtagList(hashtagList)
                .commentList(commentList)
                .quizList(quizList)
                .build();
    }

    /* method : 문제 목록 entity를 dto로 변환 */
    private List<QuizDTO> findQuizList(Test test) throws Exception {
        List<QuizDTO> quizDTOList = new ArrayList<>();

        if (test.getQuizList().size() == 0) throw new Exception("문제가 존재하지 않습니다");
        for (Quiz quiz : test.getQuizList()) {
            List<ViewDTO> viewDTOList = new ArrayList<>();
            for (View view : quiz.getViewList()) {
                ViewDTO viewDTO =
                        ViewDTO.builder()
                                .viewId(view.getViewId())
                                .viewNumber(view.getViewNumber())
                                .viewContent(view.getViewContent())
                                .build();
                viewDTOList.add(viewDTO);
            }

            viewDTOList.sort(
                    new Comparator<ViewDTO>() {
                        @Override
                        public int compare(ViewDTO v1, ViewDTO v2) {
                            return v1.getViewNumber() - v2.getViewNumber();
                        }
                    });

            QuizDTO quizDTO =
                    QuizDTO.builder()
                            .quizId(quiz.getQuizId())
                            .quizNo(quiz.getQuizNo())
                            .quizContent(quiz.getQuizContent())
                            .quizImage(quiz.getQuizImage())
                            .viewList(viewDTOList)
                            .build();
            quizDTOList.add(quizDTO);
        }

        quizDTOList.sort(
                new Comparator<QuizDTO>() {
                    @Override
                    public int compare(QuizDTO q1, QuizDTO q2) {
                        return q1.quizNo() - q2.quizNo();
                    }
                });

        return quizDTOList;
    }
}
