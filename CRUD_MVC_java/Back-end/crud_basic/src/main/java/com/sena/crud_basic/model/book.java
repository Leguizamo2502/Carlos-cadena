package com.sena.crud_basic.model;





import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


@Entity(name = "book")
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", length = 10, nullable = false)
    private int id_book;

    

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 150)
    private String description;


    // @OneToMany
    // @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    // private book_category book_category;
    
    // @OneToMany
    // @JoinColumn(name = "id_author", referencedColumnName = "id_author")
    // private book_author book_author;

    // @OneToMany
    // @JoinColumn(name = "id_publisher", referencedColumnName = "id_publisher")
    // private book_publisher book_publisher;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<book_author> book_author = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<book_category> book_category = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<book_publisher> book_publisher = new ArrayList<>();

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<loan_detail> loans = new ArrayList<>();

    public book() {
    }

    public book(int id_book, String title, String description,
            List<com.sena.crud_basic.model.book_author> book_author,
            List<com.sena.crud_basic.model.book_category> book_category,
            List<com.sena.crud_basic.model.book_publisher> book_publisher, List<loan_detail> loans) {
        this.id_book = id_book;
        this.title = title;
        this.description = description;
        this.book_author = book_author;
        this.book_category = book_category;
        this.book_publisher = book_publisher;
        this.loans = loans;
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

    public List<book_author> getBook_author() {
        return book_author;
    }

    public void setBook_author(List<book_author> book_author) {
        this.book_author = book_author;
    }

    public List<book_category> getBook_category() {
        return book_category;
    }

    public void setBook_category(List<book_category> book_category) {
        this.book_category = book_category;
    }

    public List<book_publisher> getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(List<book_publisher> book_publisher) {
        this.book_publisher = book_publisher;
    }

    public List<loan_detail> getLoans() {
        return loans;
    }

    public void setLoans(List<loan_detail> loans) {
        this.loans = loans;
    }
    
    
}
