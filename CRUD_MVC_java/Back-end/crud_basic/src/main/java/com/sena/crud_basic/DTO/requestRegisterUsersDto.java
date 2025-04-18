package com.sena.crud_basic.DTO;

public class requestRegisterUsersDto {
    private int id_user;
    private String first_name;
    private String last_name;
    
    private String identification;
    private String email;

    public requestRegisterUsersDto() {
    }

    public requestRegisterUsersDto(int id_user, String first_name, String last_name, String identification, String email) {
        this.id_user = id_user;
        this.first_name = first_name;
        this.last_name = last_name;
        this.identification = identification;
        this.email = email;
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

    
}
