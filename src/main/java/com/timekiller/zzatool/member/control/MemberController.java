package com.timekiller.zzatool.member.control;

import com.timekiller.zzatool.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberService ms;

    @GetMapping("/signup")
    public String signup(){
        return "member/signup";
    }

    // 이메일 중복 여부 확인
    @GetMapping("/emaildupchk")
    public Map<String, Integer> verifyEmail(@RequestParam String email){
        Map<String, Integer> map = new HashMap<>();
        if(ms.findEmail(email)){
            map.put("status",1);
        }else{
            map.put("status",0);
        }
        return map;
    }
}
