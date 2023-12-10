package com.example.MyWeb.demo.config.auth;

import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipal;

@Service
public class PrincipalDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌때, username,password라는 두개의 변수를 가로채는데,
    // password는 알아서 처리함.
    // username이 DB에 있는지만 확인해서 RETURN해주면 됀다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                   return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."+username);
                });
        return new PrincipalDetail(principal); //타입 변환해서 저장해줌
    }
}
