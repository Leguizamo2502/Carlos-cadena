package com.sena.crud_basic.model;

import jakarta.persistence.*;

@Entity(name = "book_author")
public class book_author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book_author", length = 10, nullable = false)
    private int id_book_author;




    // @ManyToOne
    // @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    // private book book;

    @ManyToOne
    @JoinColumn(name = "id_author", referencedColumnName = "id_author")
    private author author;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    private book book;

    public book_author() {
    }

    public book_author(int id_book_author, com.sena.crud_basic.model.author author,
            com.sena.crud_basic.model.book book) {
        this.id_book_author = id_book_author;
        this.author = author;
        this.book = book;
    }

    public int getId_book_author() {
        return id_book_author;
    }

    public void setId_book_author(int id_book_author) {
        this.id_book_author = id_book_author;
    }

    public author getAuthor() {
        return author;
    }

    public void setAuthor(author author) {
        this.author = author;
    }

    public book getBook() {
        return book;
    }

    public void setBook(book book) {
        this.book = book;
    }
    

}
