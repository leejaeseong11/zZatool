package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.TestDTO;
import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    public String main(
            Model model,
            Pageable pageable,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("search") String search,
            @RequestParam("sort") String sort,
            @RequestParam("date") int date) {
        Page<TestDTO> testList = testService.findTestList(1, pageable);
        model.addAttribute("tests", testList.getContent());
        return "main";
    }
}
