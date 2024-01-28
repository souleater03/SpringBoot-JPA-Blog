package com.example.MyWeb.demo.controller;

import com.example.MyWeb.demo.config.auth.PrincipalDetail;
import com.example.MyWeb.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;


    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 6, sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        model.addAttribute("boards",boardService.글목록(pageable));
        return "index";
    } //컨트롤러에서 세션을 어떻게 찾냐?

    @GetMapping("/board/{id}")
    public String findById(@PathVariable Long id,Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        model.addAttribute("comments",boardService.댓글목록(id));
        return "board/detail";

    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable Long id,Model model){
        model.addAttribute("board",boardService.글상세보기(id));
        return "board/updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }


}
