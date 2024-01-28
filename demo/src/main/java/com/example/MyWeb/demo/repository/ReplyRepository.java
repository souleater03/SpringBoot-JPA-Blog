package com.example.MyWeb.demo.repository;

import com.example.MyWeb.demo.dto.ReplySaveResponseDto;
import com.example.MyWeb.demo.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.MyWeb.demo.dto.ReplySaveResponseDto.convertReplyToDto;

public interface ReplyRepository extends JpaRepository<Reply,Long>, ReplyRepositoryCustom {

    }
