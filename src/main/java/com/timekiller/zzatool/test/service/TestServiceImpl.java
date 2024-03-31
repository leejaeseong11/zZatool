package com.timekiller.zzatool.test.service;

import com.timekiller.zzatool.common.service.AwsS3Service;
import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dao.TestRepository;
import com.timekiller.zzatool.test.dao.TestRepositoryCustom;
import com.timekiller.zzatool.test.dao.TestSearchCond;
import com.timekiller.zzatool.test.dto.HashtagDTO;
import com.timekiller.zzatool.test.dto.MyTestDTO;
import com.timekiller.zzatool.test.dto.TestCreateDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;
import com.timekiller.zzatool.test.entity.TestHashtag;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    // Logger 인스턴스 생성
    private static final Logger logger = Logger.getLogger(TestServiceImpl.class.getName());
    private final TestRepository testRepository;
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
    public void createTest(TestCreateDTO testCreateDTO, MultipartFile testImage) throws Exception {
        try {
            if (!Objects.isNull(testImage)) {
                String imageUrl = awsS3Service.uploadImage(testImage, profileImageUploadPath);
                testCreateDTO.setTestImage(imageUrl);
            }

            Test test =
                    Test.builder()
                            .testTitle(testCreateDTO.getTestTitle())
                            .testDate(testCreateDTO.getTestDate())
                            .testImage(testCreateDTO.getTestImage())
                            .memberId(testCreateDTO.getMemberId())
                            .build();

            testRepository.save(test);
        } catch (Exception e) {
            logger.log(Level.INFO, "테스트 생성 실패", e);
            throw new Exception("테스트 생성 실패: " + e.getMessage(), e);
        }
    }

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
}
