package com.example.demo.model.DAO;

import com.example.demo.model.entity.Article;
import com.example.demo.model.dto.ArticleUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    List<Article> findAll();

    Optional<Article> findById(int id);

    Article insert(Article article);

    void update(int id, ArticleUpdateDto updateParam);

    void deleteById(int id);
}
