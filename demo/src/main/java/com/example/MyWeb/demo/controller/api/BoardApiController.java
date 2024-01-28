package com.example.MyWeb.demo.controller.api;

import com.example.MyWeb.demo.config.auth.PrincipalDetail;
import com.example.MyWeb.demo.dto.ReplySaveRequestDto;
import com.example.MyWeb.demo.dto.ResponseDto;
import com.example.MyWeb.demo.model.Board;
import com.example.MyWeb.demo.model.Reply;
import com.example.MyWeb.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController //data만 리턴할거라!


public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable("boardId") Long id){
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("/api/board/{id}") //delete와 url이 같은데 요청 메쏘드가 다르기때문에 괜찮
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board){
        boardService.글수정하기(id,board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    //데이터를 받을때 컨트롤러에서 DTO를 만들어서 받는게좋다.
    //DTO를 사용하지않은 이유는 !!프로젝트 규모가 작아서..
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PostMapping("/api/board/{boardId}/reply/sub")
    public ResponseDto<Integer> replySubSave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.대댓글쓰기(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
