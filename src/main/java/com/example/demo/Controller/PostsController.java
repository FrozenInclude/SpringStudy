package com.example.demo.Controller;

import com.example.demo.model.dto.ArticleGetResponse;
import com.example.demo.model.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController {
    private final ArticleService articleService;

    public PostsController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public String getPosts(@RequestParam(value = "boardId") int boardId, Model model) {
        List<ArticleGetResponse> articles = articleService.getAllByBoardId(boardId);
        model.addAttribute("boardName", articleService.getBoardNameByBoardID(boardId));
        model.addAttribute("articles", articles);
        return "viewposts";
    }
}
