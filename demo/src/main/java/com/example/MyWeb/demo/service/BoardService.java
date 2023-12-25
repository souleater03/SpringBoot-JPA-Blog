package com.example.MyWeb.demo.service;

import com.example.MyWeb.demo.model.Board;
import com.example.MyWeb.demo.model.Reply;
import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.BoardRepository;
import com.example.MyWeb.demo.repository.ReplyRepository;
import com.example.MyWeb.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
        }
    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Board 글상세보기(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void 글삭제하기(int id){
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id,Board requestBoard){
        Board board = boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
        }); //영속성

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        //해당 함수로 종료시(Service가 종료될때) 트랜잭션이 종료된다. 이때 더티체킹 - 자동 업데이트가 된다. db flush
    }

    @Transactional
    public void 댓글쓰기(User user,int boardId, Reply requestReply){
       Board board = boardRepository.findById(boardId).orElseThrow(()-> {
                   return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id 값을 찾을 수 없습니다.");
               });

        requestReply.setUser(user);
        requestReply.setBoard(board);
        replyRepository.save(requestReply);
    }
}



