package com.sena.crud_basic.DTO;

import java.util.List;

public class responseBookDto {
    private int id_book;
    private String title;
    private String description;
    private List<String> publishers;
    private List<String> authors;
    private List<String> categories;
    
    // Constructor vacío
    public responseBookDto() {
    }
    
    // Constructor con todos los campos
    public responseBookDto(int id_book, String title, String description, 
                           List<String> publishers, List<String> authors, List<String> categories) {
        this.id_book = id_book;
        this.title = title;
        this.description = description;
        this.publishers = publishers;
        this.authors = authors;
        this.categories = categories;
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

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    

    

    

}
