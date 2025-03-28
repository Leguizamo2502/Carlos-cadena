package com.sena.crud_basic.model;



import java.util.List;

import jakarta.persistence.*;


@Entity(name = "auhtor")
public class author {

    public author() {
    }

    public author(Integer id_author, String firt_name, String last_name) {
        this.id_author = id_author;
        this.firt_name = firt_name;
        this.last_name = last_name;
    }

    public Integer getId_author() {
        return id_author;
    }

    public void setId_author(Integer id_author) {
        this.id_author = id_author;
    }

    public String getFirt_name() {
        return firt_name;
    }

    public void setFirt_name(String firt_name) {
        this.firt_name = firt_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author", length = 10, nullable = false)
    private Integer id_author;
    
    @Column(name="first_name",nullable = false, length = 50)
    private String firt_name;
    
    @Column(name="last_name",nullable = false, length = 50)
    private String last_name; 

    // // Relación Muchos a Muchos con Book (a través de la tabla intermedia book_author)
    // @ManyToMany(mappedBy = "authors")
    // private List<book> books;

}
