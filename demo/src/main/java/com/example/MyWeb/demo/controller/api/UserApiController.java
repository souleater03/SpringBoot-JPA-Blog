package com.example.MyWeb.demo.controller.api;

import com.example.MyWeb.demo.dto.ResponseDto;
import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //data만 리턴할거라!


public class UserApiController {
    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
    @Autowired
    private UserService userService;


    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
