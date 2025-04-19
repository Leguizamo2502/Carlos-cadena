package com.sena.crud_basic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "book_category")
public class book_category {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book_category", length = 10, nullable = false)
    private int id_book_category;


    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @JsonIgnoreProperties({"book_category"})
    private category category;

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book")
    @JsonIgnoreProperties({"book_category","description","book_author", "book_publisher", "loans" })
    private book book;

    public int getId_book_category() {
        return id_book_category;
    }

    public book_category() {
    }

    public book_category(int id_book_category, com.sena.crud_basic.model.category category,
            com.sena.crud_basic.model.book book) {
        this.id_book_category = id_book_category;
        this.category = category;
        this.book = book;
    }

    public void setId_book_category(int id_book_category) {
        this.id_book_category = id_book_category;
    }

    public category getCategory() {
        return category;
    }

    public void setCategory(category category) {
        this.category = category;
    }

    public book getBook() {
        return book;
    }

    public void setBook(book book) {
        this.book = book;
    }

    

}
