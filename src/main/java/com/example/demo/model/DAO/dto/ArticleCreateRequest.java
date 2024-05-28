package com.example.demo.model.DAO.dto;

public record ArticleCreateRequest(
        int author_id,
        int board_id,
        String title,
        String content
) {

}