package com.sena.crud_basic.DTO;

import java.util.List;

public class requestAllBookDto {
    private int id_book;
    private String title;
    private String description;
    private List<Integer> id_category;
    private List<Integer> id_publisher;
    private List<Integer> id_author;

    public requestAllBookDto() {
    }
    public requestAllBookDto(int id_book, String title, String description, List<Integer> id_category,
            List<Integer> id_publisher, List<Integer> id_author) {
        this.id_book = id_book;
        this.title = title;
        this.description = description;
        this.id_category = id_category;
        this.id_publisher = id_publisher;
        this.id_author = id_author;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Integer> getId_category() {
        return id_category;
    }
    public void setId_category(List<Integer> id_category) {
        this.id_category = id_category;
    }
    public List<Integer> getId_publisher() {
        return id_publisher;
    }
    public void setId_publisher(List<Integer> id_publisher) {
        this.id_publisher = id_publisher;
    }
    public List<Integer> getId_author() {
        return id_author;
    }
    public void setId_author(List<Integer> id_author) {
        this.id_author = id_author;
    }
    
}
