package com.sena.crud_basic.DTO;

public class requestRegisterBookAuthorDto {
    private int id_book_author;
    private int id_book;
    private int id_author;
    public requestRegisterBookAuthorDto() {
    }
    public requestRegisterBookAuthorDto(int id_book_author, int id_book, int id_author) {
        this.id_book_author = id_book_author;
        this.id_book = id_book;
        this.id_author = id_author;
    }
    public int getId_book_author() {
        return id_book_author;
    }
    public void setId_book_author(int id_book_author) {
        this.id_book_author = id_book_author;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    public int getId_author() {
        return id_author;
    }
    public void setId_author(int id_author) {
        this.id_author = id_author;
    }

    
}
