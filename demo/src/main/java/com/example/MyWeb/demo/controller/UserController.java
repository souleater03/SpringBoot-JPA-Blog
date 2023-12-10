package com.example.MyWeb.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
//인증이 필요없는 경로를 /auth/**라고 할거임
    // 그냥 주소가 / 면 index 허용
    // static이하 폴더, /js, /css, /image등 허용.
    // auth 허용.(join,login페이지에서 인증된 사용자가 필요하진 않다.)
    @GetMapping("/auth/joinForm")
    public String joinForm(){

        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){

        return "user/loginForm";
    }
}
