package com.example.demo.model;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BoardRepository {
    private final Map<Integer, Board> boards = new HashMap<>();
    public void save(Board board)
    {
        boards.put(board.getId(),board);
    }
    public void delete(int id)
    {
        boards.remove(id);
    }
    public Board find(int id)
    {
        return boards.get(id);
    }

    public Map<Integer, Board> findAll() {
        return  this.boards;
    }
}