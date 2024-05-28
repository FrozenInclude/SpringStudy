package com.example.demo.model.dto;

public record ArticleCreateRequest(
        int author_id,
        int board_id,
        String title,
        String content
) {

}