package com.timekiller.zzatool.result.control;

import com.timekiller.zzatool.result.dto.ResultDTO;
import com.timekiller.zzatool.result.service.ResultService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ResultController {
    private static final int CONTENT_SIZE = 9;
    private static final int PAGE_SIZE = 5;
    private final ResultService resultService;
    private int totalPage;
    private long totalCount;

    /* 내가 푼 테스트 조회 페이지 이동 */
    @GetMapping("/mypage/{memberId}/result/{page}")
    public String myResultList(
            @PathVariable("memberId") Long memberId,
            @PathVariable("page") Integer page,
            Model model) {
        Pageable pageable = PageRequest.of(page - 1, 9);
        Page<ResultDTO> resultList = resultService.findResultListByMemberId(memberId, pageable);
        this.totalPage = resultList.getTotalPages();
        this.totalCount = resultList.getTotalElements();

        model.addAttribute("link", "/mypage");
        model.addAttribute("memberId", memberId);
        model.addAttribute("page", page);
        model.addAttribute("results", resultList.getContent());
        model.addAttribute("startPage", 1);
        model.addAttribute("isFirstPage", true);

        int endPage = 5;
        if (PAGE_SIZE * CONTENT_SIZE >= totalCount) {
            endPage = (int) Math.ceil((double) totalCount / CONTENT_SIZE);
        }
        model.addAttribute("endPage", endPage);
        boolean isLastPage = endPage == 1;
        model.addAttribute("isLastPage", isLastPage);
        return "member/myResult";
    }
}
