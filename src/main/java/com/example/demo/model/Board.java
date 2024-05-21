package com.example.demo.model;


public class Board {
    private int id;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public  void setId(int id){this.id=id;}
    public int getId(){return this.id;}
}