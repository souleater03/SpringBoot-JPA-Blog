package com.example.MyWeb.demo.repository;


import com.example.MyWeb.demo.dto.ReplySaveResponseDto;

import com.example.MyWeb.demo.model.QReply;
import com.example.MyWeb.demo.model.Reply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.MyWeb.demo.dto.ReplySaveResponseDto.convertReplyToDto;

@RequiredArgsConstructor
@Repository
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    private final JPAQueryFactory queryFactory;



    @Override
    public List<ReplySaveResponseDto> findByBoardId(Long id) {
        QReply reply = QReply.reply;  // Use QReply

        List<Reply> replies = queryFactory.selectFrom(reply)
                .leftJoin(reply.parent).fetchJoin()
                .leftJoin(reply.user).fetchJoin()
                .where(reply.board.id.eq(id))
                .orderBy(reply.parent.id.asc().nullsFirst(),
                        reply.id.asc(),
                        reply.createDate.asc())
                .fetch();

        List<ReplySaveResponseDto> replySaveResponseDtoList = new ArrayList<>();
        Map<Long, ReplySaveResponseDto> replySaveResponseDtoHashMap = new HashMap<>();

        replies.forEach(c -> {
            ReplySaveResponseDto replySaveResponseDto = convertReplyToDto(c);
            replySaveResponseDtoHashMap.put(replySaveResponseDto.getId(), replySaveResponseDto);
            if(c.getParent() != null) replySaveResponseDtoHashMap.get(c.getParent().getId()).getChildren().add(replySaveResponseDto);
            else replySaveResponseDtoList.add(replySaveResponseDto);
        });
        return replySaveResponseDtoList;
    }
}
