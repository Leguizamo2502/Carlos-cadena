package com.sena.crud_basic.model;





import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;


@Entity(name = "author")
public class author {

    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_author", length = 10, nullable = false)
    private int id_author;
    
    @Column(name="first_name",nullable = false, length = 50)
    private String first_name;
    
    @Column(name="last_name",nullable = false, length = 50)
    private String last_name; 

    
    // @OneToMany
    // @JoinColumn(name = "id_author", referencedColumnName = "id_author")
    // private book_author book_author;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"author"})
    private List<book_author> book_author = new ArrayList<>();


    public author() {
    }


    public author(int id_author, String first_name, String last_name,
            List<com.sena.crud_basic.model.book_author> book_author) {
        this.id_author = id_author;
        this.first_name = first_name;
        this.last_name = last_name;
        this.book_author = book_author;
    }


    public int getId_author() {
        return id_author;
    }


    public void setId_author(int id_author) {
        this.id_author = id_author;
    }


    public String getFirst_name() {
        return first_name;
    }


    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }


    public String getLast_name() {
        return last_name;
    }


    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public List<book_author> getBook_author() {
        return book_author;
    }


    public void setBook_author(List<book_author> book_author) {
        this.book_author = book_author;
    }
    

}
