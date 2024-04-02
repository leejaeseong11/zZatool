package com.timekiller.zzatool.result.control;

import com.timekiller.zzatool.result.service.ResultServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private ResultServiceImpl resultService;

    /* 내가 푼 테스트 조회 페이지 이동 */
    @GetMapping("/mypage/{memberId}/result/{page}")
    public String myResultList(
            @PathVariable("memberId") Long memberId, @PathVariable("page") Integer page) {
        return "member/myResult";
    }

    /* 임시 컨트롤러
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
    */
}
