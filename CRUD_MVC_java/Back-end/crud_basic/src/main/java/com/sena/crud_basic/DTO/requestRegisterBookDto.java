package com.sena.crud_basic.DTO;

public class requestRegisterBookDto {

    private int id_book;
    private String title;
    private String description;
    public requestRegisterBookDto() {
    }
    public requestRegisterBookDto(int id_book, String title, String description) {
        this.id_book = id_book;
        this.title = title;
        this.description = description;
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
    
    
}
