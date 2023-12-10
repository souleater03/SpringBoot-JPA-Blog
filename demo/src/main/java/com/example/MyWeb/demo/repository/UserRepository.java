package com.example.MyWeb.demo.repository;

import com.example.MyWeb.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

//@Repository 생략가능 jparepository를 extends해서
public interface UserRepository extends JpaRepository<User, Integer> { //user table의 primary key는 integer
    //SELECT * FROM user WHERE username=1?;
    Optional<User> findByUsername(String username);
}
//JPA Naming전략(로그인)
// SELECT * FROM user WHERE username=?1 AND password=?2  ?엔 응답받은 값이 들어감.
// User findByUsernameAndPassword(String username,String password);

//같은메쏘드
//@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery = true)
//User login(String username, String password);
