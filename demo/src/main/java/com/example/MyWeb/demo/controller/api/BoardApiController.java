package com.example.MyWeb.demo.controller.api;

import com.example.MyWeb.demo.config.auth.PrincipalDetail;
import com.example.MyWeb.demo.dto.ResponseDto;
import com.example.MyWeb.demo.model.Board;
import com.example.MyWeb.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //data만 리턴할거라!


public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
