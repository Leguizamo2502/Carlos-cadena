package com.sena.crud_basic.DTO;


public class requestRegisterAuthorDto {
    private int id_author;
    private String first_name;
    private String last_name;
    public requestRegisterAuthorDto() {
    }
    public requestRegisterAuthorDto(int id_author, String first_name, String last_name) {
        this.id_author = id_author;
        this.first_name = first_name;
        this.last_name = last_name;
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

    

}
