package com.example.demo.model;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepository {
    private final Map<Integer, Article> articles = new HashMap<>();
    public void save(Article article)
    {
        articles.put(article.getId(), article);
    }
    public void delete(int id)
    {
        articles.remove(id);
    }
    public void edit(int id,Article update)
    {
        Article article= articles.get(id);
        article.setTitle(update.getTitle());
        article.setContent(update.getContent());
        article.setWriteDate(update.getWriteDate());
    }
    public Article find(int id)
    {
        return articles.get(id);
    }
    public Map<Integer, Article> findAll() {
        return  this.articles;
    }
}