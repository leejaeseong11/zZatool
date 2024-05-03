package com.timekiller.zzatool.test.control;

import com.timekiller.zzatool.test.dto.ViewDTO;
import com.timekiller.zzatool.test.service.ViewService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/view")
public class ViewController {
    private final ViewService viewService;

    @PostMapping("/add")
    public void add(@RequestBody ViewDTO viewDTO) {
        viewService.createView(viewDTO);
    }
}
