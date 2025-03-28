package com.sena.crud_basic.model;




import jakarta.persistence.*;

@Entity(name = "users")
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", length = 10, nullable = false)
    private Integer id_user;

    @Column(name = "first_name", nullable = false, length = 50)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 50)
    private String last_name;

    @Column(name= "identification", nullable = false, length = 10)
    private String identification;
    
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public users() {
    }

    public users(Integer id_user, String first_name, String last_name, String identification, String email) {
        this.id_user = id_user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
        this.email = email;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
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

    // @OneToMany(mappedBy = "user")
    // private List<loan> loans;

    

}
