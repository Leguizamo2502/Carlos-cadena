package com.sena.crud_basic.DTO;

public class requestRegisterBookPublisherDto {
    private int id_book_publisher;
    private int id_book;
    private int id_publisher;
    public requestRegisterBookPublisherDto() {
    }
    public requestRegisterBookPublisherDto(int id_book_publisher, int id_book, int id_publisher) {
        this.id_book_publisher = id_book_publisher;
        this.id_book = id_book;
        this.id_publisher = id_publisher;
    }
    public int getId_book_publisher() {
        return id_book_publisher;
    }
    public void setId_book_publisher(int id_book_publisher) {
        this.id_book_publisher = id_book_publisher;
    }
    public int getId_book() {
        return id_book;
    }
    public void setId_book(int id_book) {
        this.id_book = id_book;
    }
    public int getId_publisher() {
        return id_publisher;
    }
    public void setId_publisher(int id_publisher) {
        this.id_publisher = id_publisher;
    }

    
}
