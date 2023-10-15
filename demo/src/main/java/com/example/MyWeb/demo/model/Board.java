package com.example.MyWeb.demo.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
public class Board {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count;

    @ManyToOne //Many = Board , One = User 유저는 여러개의 게시글을 쓸 수 있다. board라는 table내의 설명
    @JoinColumn(name="userId")
    private User user; //db는 오브젝트를 저장 할 수 없으므로 포링키로 저장함
    @CreationTimestamp
    private Timestamp createDate;
}
