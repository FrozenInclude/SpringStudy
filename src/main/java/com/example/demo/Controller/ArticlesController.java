package com.example.demo.Controller;

import com.example.demo.model.dto.ArticleCreateRequest;
import com.example.demo.model.dto.ArticleGetResponse;
import com.example.demo.model.dto.ArticleUpdateDto;
import com.example.demo.model.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArticle(@PathVariable("id") int id) {
        try {
            ArticleGetResponse article = articleService.getById(id);
            return ResponseEntity.ok(article);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestBody ArticleCreateRequest article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.create(article));
    }

    @GetMapping
    public List<ArticleGetResponse> getArticles(@RequestParam(value = "boardId", defaultValue = "0") int boardId) {
        if (boardId == 0)
            return articleService.getAll();
        else return articleService.getAllByBoardId(boardId);
    }

    // 특정 article 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable("id") int id, @RequestBody ArticleUpdateDto updatedArticle) {
        try {
            articleService.update(id, updatedArticle);
            return ResponseEntity.ok(articleService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }

    // 특정 article 삭제
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
        try {
            articleService.delete(id);
            return ResponseEntity.ok("게시물이 지워졌습니다 :)");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }

}