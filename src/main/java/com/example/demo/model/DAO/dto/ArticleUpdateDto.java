package com.example.demo.model.DAO.dto;

import java.util.Date;

public record ArticleUpdateDto(
    int boardId,
    String title,
    String content
){}
