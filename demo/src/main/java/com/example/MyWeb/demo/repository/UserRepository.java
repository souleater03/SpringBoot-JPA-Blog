package com.example.MyWeb.demo.repository;

import com.example.MyWeb.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository 생략가능 jparepository를 extends해서
public interface UserRepository extends JpaRepository<User, Integer> { //user table의 primary key는 integer

}
