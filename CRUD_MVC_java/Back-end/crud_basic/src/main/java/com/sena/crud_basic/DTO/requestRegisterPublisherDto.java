package com.sena.crud_basic.DTO;


public class requestRegisterPublisherDto {

    private int id_publisher;
    private String name;
    
    public requestRegisterPublisherDto() {
    }

    public requestRegisterPublisherDto(int id_publisher, String name) {
        this.id_publisher = id_publisher;
        this.name = name;
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
    

}
