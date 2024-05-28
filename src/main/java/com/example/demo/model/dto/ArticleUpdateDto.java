package com.example.demo.model.dto;

import java.util.Date;

public record ArticleUpdateDto(
    int boardId,
    String title,
    String content
){}
