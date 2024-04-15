package com.timekiller.zzatool.member.control;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.timekiller.zzatool.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService ms;

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }

    // 이메일 중복 여부 확인
    @GetMapping("/emaildupchk")
    public ResponseEntity<String> findEmail(@RequestParam String email){
        if(ms.findEmail(email)){
            return ResponseEntity.ok("1");
        }else{
            return ResponseEntity.ok("0");
        }
    }

    @GetMapping("/nicknamedupchk")
    public ResponseEntity<String> findNickname(@RequestParam String nickname){
        if(ms.findNickname(nickname)){
            return ResponseEntity.ok("1");
        }else{
            return ResponseEntity.ok("0");
        }
    }

    /* 마이페이지 이동 */
    @GetMapping("/mypage/{memberId}")
    public String mypage(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("link", "/mypage");
        model.addAttribute("memberId", memberId);
        return "member/mypage";

    }
}

