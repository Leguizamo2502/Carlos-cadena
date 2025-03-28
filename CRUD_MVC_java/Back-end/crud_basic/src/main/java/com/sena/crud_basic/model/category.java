package com.sena.crud_basic.model;



import jakarta.persistence.*;

@Entity(name = "category")
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category", length = 10, nullable = false)
    private Integer id_category;

    public Integer getId_category() {
        return id_category;
    }

    public void setId_category(Integer id_category) {
        this.id_category = id_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public category() {
    }

    public category(Integer id_category, String name) {
        this.id_category = id_category;
        this.name = name;
    }

    // // Relación Uno a Muchos con Book
    // @OneToMany(mappedBy = "category")
    // private List<book> books;
}
