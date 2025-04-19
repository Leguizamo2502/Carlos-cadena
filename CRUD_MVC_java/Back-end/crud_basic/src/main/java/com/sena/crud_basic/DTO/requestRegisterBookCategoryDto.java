package com.sena.crud_basic.DTO;


public class requestRegisterBookCategoryDto {
    private int id_book_category;
    private int id_book;
    private int id_category;
    public requestRegisterBookCategoryDto() {
    }
    public requestRegisterBookCategoryDto(int id_book_category, int id_book, int id_category) {
        this.id_book_category = id_book_category;
        this.id_book = id_book;
        this.id_category = id_category;
    }
    public int getId_book_category() {
        return id_book_category;
    }
    public void setId_book_category(int id_book_category) {
        this.id_book_category = id_book_category;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    public int getId_category() {
        return id_category;
    }
    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    
}
