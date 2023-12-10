package com.example.MyWeb.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
@Entity //User 클래스로 자동 Mysql에 table 생성
public class User {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따른다.(AutoIncrement)
    private int id; //시퀀스 auto_increment

    @Column(nullable = false,length = 30)
    private String username; //아이디
    @Column(nullable = false,length = 100) //해쉬 나중에 (암호화)
    private String password;
    @Column(nullable = false,length = 50)
    private String email;

    @Enumerated(EnumType.STRING) //DB상 0,1이아닌 USER라고 저장하기위해!
    private Role role;

    @CreationTimestamp //시간 자동입력
    private Timestamp createDate; //업데이트 기능구현시 updateDate 따로 만들기.
}
