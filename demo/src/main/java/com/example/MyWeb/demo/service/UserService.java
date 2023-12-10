package com.example.MyWeb.demo.service;

import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 , ioC를 해준다
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); //원래 비번
        String encPassword = encoder.encode(rawPassword);//해쉬후 비번
        user.setPassword(encPassword);
        user.setRole(Role.USER);
        userRepository.save(user);
        }



}



