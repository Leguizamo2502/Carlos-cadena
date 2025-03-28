package com.sena.hello_world.controller;

public class responseDTO {

    private String message;

    public responseDTO(String message) {
        this.message = message;
    }

    public responseDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
