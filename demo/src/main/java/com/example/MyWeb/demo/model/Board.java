package com.example.MyWeb.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
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

    @ManyToOne(fetch = FetchType.EAGER) //Many = Board , One = User 유저는 여러개의 게시글을 쓸 수 있다. board라는 table내의 설명
    @JoinColumn(name="userId") //EAGER는 BOARD에 USER는 한건의 데이터밖에 없으니 바로 가져와 달라는뜻
    private User user; //db는 오브젝트를 저장 할 수 없으므로 포링키로 저장함

    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니라는 표기,(FK아님!!) db에 컬럼을 만들지말라는뜻, board select시 값만을 얻어오겠습니다.
    private List<Reply> reply; //LAZY는 BOARD하나당 수많은 REPLY DATA가 있으므로 필요하면 들고오겠다는 뜻
    @CreationTimestamp
    private Timestamp createDate;
}
