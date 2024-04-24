package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.exception.RemoveException;
import com.timekiller.zzatool.test.dto.MyTestDTO;
import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.entity.Test;
import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private static final int CONTENT_SIZE = 20;
    private static final int MY_TEST_CONTENT_SIZE = 9;
    private static final int PAGE_SIZE = 5;
    private final TestService testService;
    private int totalPage;
    private long totalTestCount;

    @GetMapping
    public String home(Model model, Pageable pageable) {
        Page<TestDTO> testList = testService.findTestList(pageable, 1);
        model.addAttribute("tests", testList.getContent());
        model.addAttribute("link", "/search?page=0&size=20&search=&sort=new&date=all");
        this.totalPage = testList.getTotalPages();
        this.totalTestCount = testList.getTotalElements();
        model.addAttribute("page", 0);
        model.addAttribute("size", CONTENT_SIZE);
        model.addAttribute("sort", "new");
        model.addAttribute("date", "all");
        model.addAttribute("startPage", 1);
        model.addAttribute("isFirstPage", true);
        int endPage = 5;
        if (PAGE_SIZE * CONTENT_SIZE >= totalTestCount) {
            endPage = (int) Math.ceil((double) totalTestCount / CONTENT_SIZE);
        }
        model.addAttribute("endPage", endPage);
        boolean isLastPage = endPage == 1;
        model.addAttribute("isLastPage", isLastPage);
        return "home";
    }

    @GetMapping("/search")
    public String searchHome(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "sort", defaultValue = "new") String sort,
            @RequestParam(value = "date", defaultValue = "all") String date) {
        this.totalTestCount = testService.countSearchTest(1, search, sort, date);
        this.totalPage = (int) Math.ceil((double) this.totalTestCount / CONTENT_SIZE);
        if (page >= this.totalPage) {
            page = Math.max(this.totalPage - 1, 0);
        }

        List<TestDTO> testList = testService.findSearchTestList(page, size, 1, search, sort, date);

        model.addAttribute("tests", testList);
        model.addAttribute(
                "link",
                String.format(
                        "/search?page=%s&size=%s&search=%s&sort=%s&date=%s",
                        page, size, search, sort, date));
        model.addAttribute("page", page);
        model.addAttribute("size", CONTENT_SIZE);
        model.addAttribute("sort", sort);
        model.addAttribute("date", date);
        model.addAttribute("search", search);
        int startPage = page / PAGE_SIZE * PAGE_SIZE + 1;
        model.addAttribute("startPage", startPage);
        model.addAttribute("isFirstPage", startPage == 1);
        int endPage = startPage + (PAGE_SIZE - 1);
        boolean isLastPage = false;
        if ((int) (page / PAGE_SIZE) == (int) (totalPage / PAGE_SIZE)) {
            endPage = Math.max((int) Math.ceil((double) totalTestCount / CONTENT_SIZE), 1);
            isLastPage = true;
        }

        log.info("endpage={}", endPage);
        log.info("totalTestCount={}", totalTestCount);
        model.addAttribute("endPage", endPage);
        model.addAttribute("isLastPage", isLastPage);

        return "home";
    }

    @GetMapping("/test/add")
    public String addForm(Model model) {
        model.addAttribute("test", TestDTO.builder().build());

        return "test/form";
    }

    @PostMapping("/test/add")
    public String add(@ModelAttribute TestDTO testDTO) throws Exception {
        Test savedTest = testService.createTest(testDTO);
        return "redirect:/test/" + savedTest.getTestId() + "/quiz/add?viewCount=0";
    }

    //    @PostMapping("/test")
    //    public ResponseEntity<?> createTest(
    //            @RequestPart(name = "testDTO") TestCreateDTO testCreateDTO,
    //            @RequestPart(name = "testImage", required = false) MultipartFile testImage) {
    //        try {
    //            testService.createTest(testCreateDTO, testImage);
    //            return ResponseEntity.ok().body("테스트 생성이 완료되었습니다.");
    //        } catch (Exception e) {
    //            return ResponseEntity.badRequest().body("테스트 생성에 실패했습니다.");
    //        }
    //    }

    @DeleteMapping("/test/{testId}")
    public ResponseEntity<?> deleteTest(@PathVariable Long testId, @RequestParam Long memberId) {
        try {
            testService.deleteTest(testId, memberId);
            return ResponseEntity.ok().body("테스트 삭제가 완료되었습니다.");
        } catch (RemoveException e) {
            return ResponseEntity.badRequest().body("테스트를 삭제할 수 없습니다.");
        }
    }

    /* 내가 만든 테스트 조회 페이지 이동 */
    @GetMapping("/mypage/{memberId}/test/{order}/{page}")
    public String mytestList(
            @PathVariable("memberId") Long memberId,
            @PathVariable("order") String order,
            @PathVariable("page") Integer page,
            Model model)
            throws Exception {
        Pageable pageable = PageRequest.of(page - 1, 9);

        try {
            Page<MyTestDTO> testList =
                    testService.findTestListByMemberId(memberId, order, pageable);
            this.totalPage = testList.getTotalPages();
            this.totalTestCount = testList.getTotalElements();
            model.addAttribute("tests", testList.getContent());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        model.addAttribute("link", "/mypage");
        model.addAttribute("memberId", memberId);
        model.addAttribute("order", order);
        model.addAttribute("page", page);
        model.addAttribute("startPage", 1);
        model.addAttribute("isFirstPage", true);

        int endPage = 5;
        if (PAGE_SIZE * MY_TEST_CONTENT_SIZE >= totalTestCount) {
            endPage = (int) Math.ceil((double) totalTestCount / MY_TEST_CONTENT_SIZE);
        }
        model.addAttribute("endPage", endPage);
        boolean isLastPage = endPage == 1;
        model.addAttribute("isLastPage", isLastPage);

        return "member/myTest";
    }

    /* 테스트 조회 페이지 이동 */
    @GetMapping("test/{testId}")
    public String testInfo(@PathVariable("testId") Long testId, Model model) throws Exception {
        model.addAttribute("testId", testId);

        try {
            TestDTO testDTO = testService.findTestByTestId(testId);
            model.addAttribute("test", testDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "test/testInfo";
    }

    /* 테스트 시작 페이지 이동 */
    @GetMapping("test/start/{testId}")
    public String testStart(@PathVariable("testId") Long testId, Model model) throws Exception {
        model.addAttribute("testId", testId);

        try {
            TestDTO testDTO = testService.findTestByTestId(testId);
            model.addAttribute("test", testDTO);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return "test/testStart";
    }
}
