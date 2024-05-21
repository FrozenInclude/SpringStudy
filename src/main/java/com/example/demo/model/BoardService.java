package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    private int nextId = 1;

    public Board add(Board board) {
        board.setId(nextId++);
        boardRepository.save(board);
        return get(nextId - 1);
    }

    public void remove(int id)
    {
        boardRepository.delete(id);
    }

    public Board get(int id) {
        return boardRepository.find(id);
    }

    public String getTitleById(int id) {
        return boardRepository.find(id).getTitle();
    }

    public ArrayList<Board> getAll()
    {
        return new ArrayList<>(boardRepository.findAll().values());
    }

}
