package com.example.demo.model.service;

import com.example.demo.model.entity.Article;
import com.example.demo.model.entity.Board;
import com.example.demo.model.entity.Member;
import com.example.demo.model.DAO.ArticleDAO;
import com.example.demo.model.DAO.BoardDAO;
import com.example.demo.model.DAO.MemberDAO;
import com.example.demo.model.dto.ArticleCreateRequest;
import com.example.demo.model.dto.ArticleGetResponse;
import com.example.demo.model.dto.ArticleUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ArticleService {
    private final ArticleDAO articleDAO;
    private final MemberDAO memberDAO;
    private final BoardDAO boardDAO;

    public ArticleService(ArticleDAO articleDAO,
                          MemberDAO memberDAO,
                          BoardDAO boardDAO
    ) {
        this.articleDAO = articleDAO;
        this.memberDAO = memberDAO;
        this.boardDAO = boardDAO;
    }

    public List<ArticleGetResponse> getAll() {
        List<Article> articles = articleDAO.findAll();
        return articles.stream()
                .map(article -> {
                    Optional<Member> member = memberDAO.findById(article.getAuthor_id());
                    Optional<Board> board = boardDAO.findById(article.getBoard_id());
                    if (member.isEmpty() || board.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    return ArticleGetResponse.from(article, member.get(), board.get());
                })
                .toList();
    }

    public List<ArticleGetResponse> getAllByBoardId(int board_id) {
        List<Article> articles = articleDAO.findAll();
        return articles.stream().filter(article -> article.getBoard_id() == board_id)
                .map(article -> {
                    Optional<Member> member = memberDAO.findById(article.getAuthor_id());
                    Optional<Board> board = boardDAO.findById(article.getBoard_id());
                    if (member.isEmpty() || board.isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    return ArticleGetResponse.from(article, member.get(), board.get());
                })
                .toList();
    }

    public String getBoardNameByBoardID(int boardID) {
        Board board = boardDAO.findById(boardID)
                .orElseThrow(IllegalArgumentException::new);
        return board.getName();
    }

    public ArticleGetResponse getById(int id) {
        Article article = articleDAO.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        Member member = memberDAO.findById(article.getAuthor_id())
                .orElseThrow(IllegalArgumentException::new);

        Board board = boardDAO.findById(article.getBoard_id())
                .orElseThrow(IllegalArgumentException::new);

        return ArticleGetResponse.from(article, member, board);
    }

    @Transactional
    public ArticleGetResponse create(ArticleCreateRequest request) {
        Article article = new Article();

        article.setAuthor_id(request.author_id());
        article.setBoard_id(request.board_id());
        article.setTitle(request.title());
        article.setContent(request.content());
        article.setCreated_date(new Date());

        Article saved = articleDAO.insert(article);
        Member member = memberDAO.findById(saved.getAuthor_id())
                .orElseThrow(IllegalArgumentException::new);
        Board board = boardDAO.findById(article.getBoard_id())
                .orElseThrow(IllegalArgumentException::new);
        return ArticleGetResponse.from(article, member, board);
    }

    @Transactional
    public ArticleGetResponse update(int id, ArticleUpdateDto request) {
        Article article = articleDAO.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        ;
        articleDAO.update(id, request);
        Article updated = articleDAO.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        Member member = memberDAO.findById(updated.getAuthor_id())
                .orElseThrow(IllegalArgumentException::new);
        Board board = boardDAO.findById(article.getBoard_id())
                .orElseThrow(IllegalArgumentException::new);
        return ArticleGetResponse.from(article, member, board);
    }

    @Transactional
    public void delete(int id) {
        articleDAO.deleteById(id);
    }
}
