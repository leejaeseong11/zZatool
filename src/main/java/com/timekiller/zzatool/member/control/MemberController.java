package com.timekiller.zzatool.member.control;

import com.timekiller.zzatool.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService ms;

    // 중복 여부 확인 및 인증 메일 전송
    @RequestMapping(value = "/mailverify", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam String email, Model model){
        if(!ms.findEmail(email)){
            model.addAttribute("message", "ok");
        }else{
            model.addAttribute("message", "이미 계정이 존재합니다.");
        }

        return "/signup.html";
    }
}
