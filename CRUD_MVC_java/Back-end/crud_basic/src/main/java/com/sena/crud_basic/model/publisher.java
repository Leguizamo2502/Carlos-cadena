package com.sena.crud_basic.model;

import java.util.List;

import jakarta.persistence.*;

@Entity(name = "publisher")
public class publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publisher", length = 10, nullable = false)
    private Integer id_publisher;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public publisher() {
    }

    public Integer getId_publisher() {
        return id_publisher;
    }

    public void setId_publisher(Integer id_publisher) {
        this.id_publisher = id_publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public publisher(Integer id_publisher, String name) {
        this.id_publisher = id_publisher;
        this.name = name;
    }

    // // Relación Muchos a Muchos con Book (a través de la tabla intermedia book_publisher)
    // @ManyToMany(mappedBy = "publishers")
    // private List<book> books;
}
