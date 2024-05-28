package com.example.demo.model.entity;

import java.util.Date;

public class Article {
    private int id;
    private int author_id;
    private int board_id;
    private String title;
    private String content;
    private Date created_date;
    private Date modified_date;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return this.author_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getBoard_id() {
        return this.board_id;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return this.created_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    public Date getModified_date() {
        return this.modified_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
