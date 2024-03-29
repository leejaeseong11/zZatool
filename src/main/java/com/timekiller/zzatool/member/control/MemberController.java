package com.timekiller.zzatool.member.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/signup")
    public String signup(){
        return "/signup.html";
    }
}
