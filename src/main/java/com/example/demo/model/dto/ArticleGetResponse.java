package com.example.demo.model.dto;

import com.example.demo.model.entity.Article;
import com.example.demo.model.entity.Board;
import com.example.demo.model.entity.Member;

import java.text.SimpleDateFormat;

public record ArticleGetResponse(
        int id,
        int author_id,
        int board_id,
        String title,
        String content,
        String created_date,
        String modified_date
) {
    public static ArticleGetResponse from(Article article, Member member, Board board) {
        return new ArticleGetResponse(
                article.getId(),
                member.getId(),
                board.getId(),
                article.getTitle(),
                article.getContent(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getCreated_date()),
                article.getModified_date() != null ?
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(article.getModified_date()) : "N/A"
        );
    }
}