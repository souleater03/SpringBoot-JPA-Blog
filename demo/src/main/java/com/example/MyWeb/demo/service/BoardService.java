package com.example.MyWeb.demo.service;

import com.example.MyWeb.demo.model.Board;
import com.example.MyWeb.demo.model.Role;
import com.example.MyWeb.demo.model.User;
import com.example.MyWeb.demo.repository.BoardRepository;
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

    @Transactional
    public void 글쓰기(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
        }

    public Page<Board> 글목록(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    public Board 글상세보기(int id){
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

}



