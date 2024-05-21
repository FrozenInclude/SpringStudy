package com.example.demo.model;

public class postsEntity {
    String title;
    String authorName;
    String date;
    String content;

    public postsEntity(String title, String authorName, String date, String content) {
        this.title = title;
        this.authorName = authorName;
        this.date = date;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
