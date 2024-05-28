package com.example.demo.model.DAO;

import com.example.demo.model.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcBoardDAO implements BoardDAO {
    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query("select * from board", boardRowMapper());
    }

    @Override
    public Board insert(Board board) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("board").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", board.getName());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        board.setId(key.intValue());
        return board;
    }

    @Override
    public Optional<Board> findById(int id) {
        List<Board> result = jdbcTemplate.query("select * from board where id = ?", boardRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<Board> boardRowMapper() {
        return (rs, rowNum) -> {
            Board board = new Board();
            board.setId(rs.getInt("id"));
            board.setName(rs.getString("name"));
            return board;
        };
    }
}
