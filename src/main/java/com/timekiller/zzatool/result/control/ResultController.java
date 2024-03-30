package com.timekiller.zzatool.result.control;

import com.timekiller.zzatool.result.dto.ResultDTO;
import com.timekiller.zzatool.result.service.ResultServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ResultController {
    @Autowired private ResultServiceImpl resultService;

    /* 임시 컨트롤러 */
    @GetMapping("/result")
    public Page<ResultDTO> myResultList(
            @RequestParam(value = "memberId") Long memberId, Pageable pageable) {
        return resultService.findResultListByMemberId(memberId, pageable);
    }
}
