package com.sena.crud_basic.DTO;

import java.util.List;

public interface responseLoanDto {
    Integer getId_loan(); 
    String getFullName(); // Aquí recibimos el nombre completo como una cadena.
    String getBookTitle(); 
    Integer getQuantity(); 

}
