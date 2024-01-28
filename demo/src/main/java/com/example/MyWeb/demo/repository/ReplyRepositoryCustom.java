package com.example.MyWeb.demo.repository;

import com.example.MyWeb.demo.dto.ReplySaveResponseDto;
import com.example.MyWeb.demo.model.Reply;

import java.util.List;

public interface ReplyRepositoryCustom {
    List<ReplySaveResponseDto> findByBoardId(Long id);
}
