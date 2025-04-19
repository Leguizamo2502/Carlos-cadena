package com.sena.crud_basic.model;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "publisher")
public class publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_publisher", length = 10, nullable = false)
    private int id_publisher;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    

    // @OneToMany
    // @JoinColumn(name = "id_publisher", referencedColumnName = "id_publisher")
    // private book_publisher book_publisher;
    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"publisher"})
    private List<book_publisher> book_publisher = new ArrayList<>();



    public publisher() {
    }



    public publisher(int id_publisher, String name, List<com.sena.crud_basic.model.book_publisher> book_publisher) {
        this.id_publisher = id_publisher;
        this.name = name;
        this.book_publisher = book_publisher;
    }



    public int getId_publisher() {
        return id_publisher;
    }



    public void setId_publisher(int id_publisher) {
        this.id_publisher = id_publisher;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public List<book_publisher> getBook_publisher() {
        return book_publisher;
    }



    public void setBook_publisher(List<book_publisher> book_publisher) {
        this.book_publisher = book_publisher;
    }




    
}
