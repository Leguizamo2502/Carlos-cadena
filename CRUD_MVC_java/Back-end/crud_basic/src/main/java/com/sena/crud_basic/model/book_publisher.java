package com.sena.crud_basic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "book_publisher")
public class book_publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book_publisher", length = 10, nullable = false)
    private int id_book_publisher;


    @ManyToOne
    @JoinColumn(name = "id_publisher", referencedColumnName = "id_publisher")
    // @JsonIgnoreProperties({"book_publisher"})
    private publisher publisher;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    // @JsonIgnoreProperties({"book_publisher","description","book_author", "book_category", "loans" })
    private book book;

    public book_publisher() {
    }

    public book_publisher(int id_book_publisher, com.sena.crud_basic.model.publisher publisher,
            com.sena.crud_basic.model.book book) {
        this.id_book_publisher = id_book_publisher;
        this.publisher = publisher;
        this.book = book;
    }

    public int getId_book_publisher() {
        return id_book_publisher;
    }

    public void setId_book_publisher(int id_book_publisher) {
        this.id_book_publisher = id_book_publisher;
    }

    public publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(publisher publisher) {
        this.publisher = publisher;
    }

    public book getBook() {
        return book;
    }

    public void setBook(book book) {
        this.book = book;
    }
    
}
