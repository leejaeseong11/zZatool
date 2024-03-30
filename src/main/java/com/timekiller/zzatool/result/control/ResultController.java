package com.timekiller.zzatool.result.control;

import com.timekiller.zzatool.result.dto.ResultDTO;
import com.timekiller.zzatool.result.service.ResultServiceImpl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired private ResultServiceImpl resultService;

    /* 임시 컨트롤러 */
    @GetMapping("/{memberId}/{page}")
    public Page<ResultDTO> myResultList(
            @PathVariable("memberId") Long memberId, @PathVariable("page") Integer page)
            throws Exception {
        try {
            Pageable pageable = PageRequest.of(page - 1, 20);
            return resultService.findResultListByMemberId(memberId, pageable);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
