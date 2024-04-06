package com.timekiller.zzatool.member.control;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MemberController {

    /* 마이페이지 이동 */
    @GetMapping("/mypage/{memberId}")
    public String mypage(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("link", "/mypage");
        model.addAttribute("memberId", memberId);
        return "member/mypage";
    }
}
