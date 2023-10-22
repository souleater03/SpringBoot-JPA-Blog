package com.example.MyWeb.demo.test;

import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    //http://localhost:8000/blog/dummy/join (요청)
    //http의 body에 username, password, email 데이터를 가지고 (요청)

    @Autowired
    private UserRepository userRepository;
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username =" +user.getUsername());
        System.out.println("password =" +user.getPassword());
        System.out.println("email =" +user.getEmail());

        user.setRole(Role.USER);

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
