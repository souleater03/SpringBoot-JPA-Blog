package com.example.MyWeb.demo.model;

import com.example.MyWeb.demo.dto.ReplySaveRequestDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Reply> children = new ArrayList<>();

    @ManyToOne //여러개의 답변은 하나의 게시물만 특정한다.
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne //여러개의 답변은 하나의 유저가 쓸수있다.
    @JoinColumn(name = "userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;

    public void update(User user,Board board,String content, Boolean isDeleted){
        this.user = user;
        this.board = board;
        this.content = content;
        this.isDeleted = isDeleted;
    }

    public void updateParent(Reply parent){
        this.parent = parent;
    }

 //idea: 부모댓글일경우 부모댓글id 값은 null이며
    //자식댓글일 경우 해당 부모댓글의 댓글id값을 부모id값으로 가지면 됀다.

}
