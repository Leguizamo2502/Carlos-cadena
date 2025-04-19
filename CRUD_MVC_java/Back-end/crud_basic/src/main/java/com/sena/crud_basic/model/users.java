package com.sena.crud_basic.model;




import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", length = 10, nullable = false)
    private int id_user;

    @Column(name = "first_name", nullable = false, length = 50)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String last_name;

    @Column(name= "identification", nullable = false, length = 10)
    private String identification;
    
    @Column(name = "email", nullable = false, length = 50)
    private String email;

   @OneToMany(mappedBy  = "users", cascade = CascadeType.ALL, orphanRemoval = true)
   @JsonIgnoreProperties({"users","loans"})
   private List<loan> loans = new ArrayList<>();

   public users() {
   }

   public users(int id_user, String first_name, String last_name, String identification, String email,
        List<loan> loans) {
    this.id_user = id_user;
    this.first_name = first_name;
    this.last_name = last_name;
    this.identification = identification;
    this.email = email;
    this.loans = loans;
   }

   public int getId_user() {
    return id_user;
   }

   public void setId_user(int id_user) {
    this.id_user = id_user;
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

   public String getIdentification() {
    return identification;
   }

   public void setIdentification(String identification) {
    this.identification = identification;
   }

   public String getEmail() {
    return email;
   }

   public void setEmail(String email) {
    this.email = email;
   }

   public List<loan> getLoans() {
    return loans;
   }

   public void setLoans(List<loan> loans) {
    this.loans = loans;
   }

    

}
