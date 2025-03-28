package com.sena.crud_basic.model;



import java.util.List;

import jakarta.persistence.*;


@Entity(name = "book")
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", length = 10, nullable = false)
    private Integer id_book;

    public book() {
    }

    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
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

    public book(Integer id_book, String title, String description) {
        this.id_book = id_book;
        this.title = title;
        this.description = description;
    }

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 150)
    private String description;


    // // Relación Muchos a Muchos con Author (a través de la tabla intermedia Book_Author)
    // @ManyToMany
    // @JoinTable(
    //     name = "book_author",
    //     joinColumns = @JoinColumn(name = "id_book"),
    //     inverseJoinColumns = @JoinColumn(name = "id_author")
    // )
    // private List<author> authors;

    // // Relación Muchos a Muchos con Category (a través de la tabla intermedia Book_Category)
    // @ManyToMany
    // @JoinTable(
    //     name = "book_category",
    //     joinColumns = @JoinColumn(name = "id_book"),
    //     inverseJoinColumns = @JoinColumn(name = "id_category")
    // )
    // private List<category> categories;

    // // Relación Muchos a Muchos con Publisher (a través de la tabla intermedia Book_Publisher)
    // @ManyToMany
    // @JoinTable(
    //     name = "book_publisher",
    //     joinColumns = @JoinColumn(name = "id_book"),
    //     inverseJoinColumns = @JoinColumn(name = "id_publisher")
    // )
    // private List<publisher> publishers;

    // // Relación Uno a Muchos con LoanDetail (para registrar préstamos de este libro)
    // @OneToMany(mappedBy = "book")
    // private List<loan_detail> loanDetails;
    
}
