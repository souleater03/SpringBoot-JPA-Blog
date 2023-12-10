package com.example.MyWeb.demo.controller;

import com.example.MyWeb.demo.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping({"","/"})
    public String index(){
        return "index";
    } //컨트롤러에서 세션을 어떻게 찾냐?

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }
}
