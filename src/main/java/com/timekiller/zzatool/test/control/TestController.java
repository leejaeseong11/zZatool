package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {
    private final TestService testService;

    @GetMapping
    public String home(Model model, Pageable pageable) {
        Page<TestDTO> testList = testService.findTestList(pageable, 1);
        log.info("test data={}", testList);
        model.addAttribute("tests", testList.getContent());
        return "home";
    }

    @GetMapping("/search")
    public String searchHome(
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "date", required = false) String date) {
        List<TestDTO> testList = testService.findSearchTestList(page, size, 1, search, sort, date);
        log.info("test data={}", testList);
        model.addAttribute("tests", testList);
        return "home";
    }
}
