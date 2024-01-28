package com.example.MyWeb.demo.dto;

import com.example.MyWeb.demo.model.Reply;
import com.example.MyWeb.demo.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplySaveResponseDto {
    private Long id;
    private String content;
    private User user;
    private List<ReplySaveResponseDto> children = new ArrayList<>();

    public ReplySaveResponseDto(Long id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public static ReplySaveResponseDto convertReplyToDto(Reply reply) {
        return reply.getIsDeleted() ?
                new ReplySaveResponseDto(reply.getId(), "삭제된 댓글입니다.", null) :
                new ReplySaveResponseDto(reply.getId(), reply.getContent(), reply.getUser());
    }


}
