package com.sena.crud_basic.DTO;

import org.springframework.http.HttpStatus;

public class responseDto {
    private HttpStatus status;
    private String message;
    public responseDto() {
    }
    public responseDto(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
