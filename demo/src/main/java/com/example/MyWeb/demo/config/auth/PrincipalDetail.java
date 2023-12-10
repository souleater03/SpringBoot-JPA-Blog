package com.example.MyWeb.demo.config.auth;

import com.example.MyWeb.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다. 기능 = 저장, 변환
public class PrincipalDetail implements UserDetails {
    private User user; //객체 품기 = 콤포지션

    //이렇게 생성자로 user값을 지정 안해주면 null로만 지정됄것.
    public PrincipalDetail(User user){
        this.user = user;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //계정이 만료되지 않았는지 return한다. (true= 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 잠기진 않았는지 return한다. (true= 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비번이 만료되진 않았는지 return한다. (true= 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정이 활성화 되었는지 return한다. (true = 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을수 있어서 사실은 루프를 돌아야 한다,for문)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return "ROLE_"+user.getRole(); //return 값 : ROLE_USER .. 스프링의 ROLE값에대한 규칙이므로 "ROLE_"은 꼭 추가해준다.(prefix 중요!!)
//            }
//        });

        //중요!! collectors.add안에 들어올 타입은 GrantedAuthority() 밖에 없게 되어있다. 따라서 자바 특성상 메소드를 포함시키긴 힘드니 람다식을 써서 코드를 줄인다.
        collectors.add(()->{ return "ROLE_"+user.getRole();});
        return collectors;
    }

}
