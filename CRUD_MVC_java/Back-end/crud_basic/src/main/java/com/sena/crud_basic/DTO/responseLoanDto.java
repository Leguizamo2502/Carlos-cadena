package com.sena.crud_basic.DTO;

import java.time.LocalDate;


public interface responseLoanDto {
    Integer getId_loan(); 
    String getFullName(); // Aquí recibimos el nombre completo como una cadena.
    String getStatus();
    String getBookTitle(); 
    LocalDate getLoan_date();
    LocalDate getReturn_date();
    // Integer getQuantity(); 

}
