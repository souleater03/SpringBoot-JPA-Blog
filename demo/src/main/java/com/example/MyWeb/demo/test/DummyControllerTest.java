package com.example.MyWeb.demo.test;

import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

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

    //{id} 주소로 파라미터 전달받기 가능
    //http://localhost:8000/blog/dummy/user/3 이런식
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //리턴타입이 optional임  user/4찾게 될때 db에 없음 null이됌.
        //그럼 null이 return됨으로 문제가 생김. 그래서 optional로 감싸서 가져오니 null인지 아닌지 판단해서 return하라는것
        //User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
          //  @Override
            //public IllegalArgumentException get(){
              //  return new IllegalArgumentException("해당 유저는 없습니다. id:" +id);
            //}
        //});
        //return user;
        
        //람다식으로 함

        User user = userRepository.findById(id).orElseThrow(()->{
           return new IllegalArgumentException("해당 유저는 없습니다.");
        });
        //user객체 = 자바오브젝트. restcontroller 는 html파일이 아닌 data를 return함
        //그럼 web browser은 user객체를 이해못함.
        //그래서 json으로 변환해야 함. browser가 이해할수있게.
        // 스프링 부트 = MessageConverter라는 애가 응답시에 자동 작동
        //만약에 자바오브젝트를 리턴하게 되면 MessageConverter가 Jackson이라는 라이브러리를 호출해서
        //user오브젝트를 json으로 변환해서 브라우저에게 던져준다.
            return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //한 페이지당 10건의 회원 목록을 받아옴
    //DESC = ID 최신순
    //http://localhost:8000/blog/dummy/user?page=1 이런식
    @GetMapping("dummy/user")
    public List<User> pageList(@PageableDefault(size = 10,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser =  userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
      return users;
    }

    //email, password 두개 수정할거임
    //주소가 같아도 get 이랑 put차이라 알아서 구분함
    //@RequestBody를 하면 requestUser에 data를 담아 매개변수로서 세울수있음.
    //save메소드는 data가 없을때는 insert, 있을때는 update를 해준다.
    @Transactional //save호출안해도됨, 더티체킹: 이 어노테이션을 걸어주면 변경점을 찾아 자동 commit해줌,변경감지!!(이게 더티체킹이다.)
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){

        User user =userRepository.findById(id).orElseThrow(()->{ //여기서 user객체는 영속화!(entity)
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);
        return null;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            return "삭제 실패 하였습니다. 해당 id는 DB에 없습니다.";
        }


        return "삭제 되었습니다. id" +id;
    }
}
