package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    public String home(
            Model model,
            Pageable pageable,
            @RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "size", required = false) int size,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "date", required = false) int date) {
        Page<TestDTO> testList = testService.findTestList(1, pageable);
        model.addAttribute("tests", testList.getContent());
        return "home";
    }
}
