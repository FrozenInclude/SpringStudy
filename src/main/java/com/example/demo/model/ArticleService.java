package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    private int nextId = 1;

    public Article create(Article article) {
        article.setId(nextId++);
        article.setWriteDate(new Date());
        articleRepository.save(article);
        return get(nextId - 1);
    }

    public Article get(int id) {
        return articleRepository.find(id);
    }

    public ArrayList<Article> getAll() {
        return new ArrayList<>(articleRepository.findAll().values());
    }

    public Article update(int id, Article article) {
        article.setEditDate(new Date());
        articleRepository.edit(id, article);
        return get(id);
    }

    public void delete(int id) {
        articleRepository.delete(id);
    }
}
