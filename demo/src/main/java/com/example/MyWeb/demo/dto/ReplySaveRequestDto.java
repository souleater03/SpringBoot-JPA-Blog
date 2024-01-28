package com.example.MyWeb.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplySaveRequestDto {
    private Long userId;
    private Long boardId;
    private Long parentId;
    private String content;

    public ReplySaveRequestDto(String content){
        this.content = content;
    }
}
