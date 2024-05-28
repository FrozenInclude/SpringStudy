package com.example.demo.model.DAO;

import com.example.demo.model.entity.Board;

import java.util.List;
import java.util.Optional;


public interface BoardDAO {
    List<Board> findAll();

    Optional<Board> findById(int id);

    Board insert(Board board);

    void deleteById(int id);
}