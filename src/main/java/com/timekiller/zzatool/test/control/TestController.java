package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.service.TestService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping
    public String main() {
        return "main";
    }
}
