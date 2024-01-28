package com.example.MyWeb.demo.repository;

import com.example.MyWeb.demo.model.Board;
import com.example.MyWeb.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository 생략가능 jparepository를 extends해서
public interface BoardRepository extends JpaRepository<Board, Long> { //user table의 primary key는 integer
    //SELECT * FROM user WHERE username=1?;

}
