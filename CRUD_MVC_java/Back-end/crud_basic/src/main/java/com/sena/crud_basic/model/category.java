package com.sena.crud_basic.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity(name = "category")
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", length = 10, nullable = false)
    private int id_category;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    // // Relación Uno a Muchos con Book
    // @OneToMany(mappedBy = "category")
    // private List<book> books;
    // @OneToMany
    // @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    // private book_category book_category;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval =
    true)
    private List<book_category> book_category = new ArrayList<>();
    // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<book_category> book_category = new ArrayList<>();

    public category() {
    }

    public category(int id_category, String name, List<com.sena.crud_basic.model.book_category> book_category) {
        this.id_category = id_category;
        this.name = name;
        this.book_category = book_category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<book_category> getBook_category() {
        return book_category;
    }

    public void setBook_category(List<book_category> book_category) {
        this.book_category = book_category;
    }

}
