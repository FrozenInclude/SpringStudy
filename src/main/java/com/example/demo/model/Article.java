package com.example.demo.model;

import javax.xml.crypto.Data;
import java.util.Date;

public class Article {
    private int id;
    private int authorId;
    private int boardId;
    private String title;
    private String content;
    private Date writeDate;
    private Date editDate;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getAuthorId() {
        return this.authorId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getBoardId() {
        return this.boardId;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Date getWriteDate() {
        return this.writeDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Date getEditDate() {
        return this.editDate;
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
