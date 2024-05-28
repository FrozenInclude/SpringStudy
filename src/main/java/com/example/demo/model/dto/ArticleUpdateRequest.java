package com.example.demo.model.dto;

public record ArticleUpdateRequest(
    int boardId,
    String title,
    String content
){}
