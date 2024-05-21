package com.example.demo.IntroController;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class controller {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private BoardService boardService;

    // 특정 article 조회
    @GetMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<?> getArticle(@PathVariable("id") int id) {
        Article article = articleService.get(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }


    // 새로운 article 생성
    @PostMapping("/articles")
    @ResponseBody
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        articleService.create(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    //test용 게시판이랑 회원 확보
    @PostMapping("/test")
    @ResponseBody
    public String makeTest() {
        Member test = new Member();
        Board test2 = new Board();
        test.setName("테스터");
        test2.setTitle("자유게시판");
        memberService.join(test);
        boardService.add(test2);
        return "테스트셋 만듬";
    }

    @GetMapping("/articles")
    @ResponseBody
    public ArrayList<Article> getArticles() {
        return articleService.getAll();
    }

    @GetMapping("/posts")
    public String viewAllArticles(Model model) {
        Map<String, ArrayList<postsEntity>> viewData = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        boardService.getAll().forEach(board ->
                viewData.put(board.getTitle(), new ArrayList<postsEntity>())
        );

        articleService.getAll().forEach(article -> {
            String boardTitle = boardService.getTitleById(article.getBoardId());
            String authorName = memberService.getNameById(article.getAuthorId());
            String formattedDate = dateFormat.format(article.getWriteDate());

            postsEntity post = new postsEntity(article.getTitle(), authorName, formattedDate, article.getContent());
            viewData.get(boardTitle).add(post);
        });

        model.addAttribute("viewData", viewData);
        return "viewposts";
    }

    // 특정 article 정보 수정
    @PutMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<?> updateArticle(@PathVariable("id") int id, @RequestBody Article updatedArticle) {
        Article article = articleService.get(id);
        if (article != null) {
            articleService.update(id, updatedArticle);
            return ResponseEntity.ok(article);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }

    // 특정 article 삭제
    @DeleteMapping("/articles/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteArticle(@PathVariable("id") int id) {
        Article article = articleService.get(id);
        if (article != null) {
            articleService.delete(id);
            return ResponseEntity.ok("게시물이 지워졌습니다 :)");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시물을 찾을수 없습니다 :(");
        }
    }

}