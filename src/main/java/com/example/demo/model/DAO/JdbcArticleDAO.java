package com.example.demo.model.DAO;

import com.example.demo.model.entity.Article;
import com.example.demo.model.dto.ArticleUpdateRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;

@Component
public class JdbcArticleDAO implements ArticleDAO {
    private final JdbcTemplate jdbcTemplate;

    public JdbcArticleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from article", articleRowMapper());
    }

    @Override
    public Article insert(Article ar) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("article").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("board_id", ar.getBoard_id());
        parameters.put("author_id", ar.getAuthor_id());
        parameters.put("title", ar.getTitle());
        parameters.put("content", ar.getContent());
        parameters.put("created_date", ar.getCreated_date());
        parameters.put("modified_date", ar.getModified_date());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        ar.setId(key.intValue());
        return ar;
    }

    @Override
    public Optional<Article> findById(int id) {
        List<Article> result = jdbcTemplate.query("select * from article where id = ?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public void update(int id, ArticleUpdateRequest updateParam) {
        String sql = "update article set board_id=?, title=?, content=?, modified_date=? where id = ?";
        jdbcTemplate.update(sql,
                updateParam.boardId(),
                updateParam.title(),
                updateParam.content(),
                new Timestamp(System.currentTimeMillis()),
                id);
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCreated_date(rs.getTimestamp("created_date"));
            article.setBoard_id(rs.getInt("board_id"));
            article.setAuthor_id(rs.getInt("author_id"));
            article.setModified_date(rs.getTimestamp("modified_date"));
            return article;
        };
    }
}
