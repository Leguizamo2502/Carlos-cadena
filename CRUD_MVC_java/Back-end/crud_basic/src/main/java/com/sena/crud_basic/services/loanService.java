package com.sena.crud_basic.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestAllLoanDto;
import com.sena.crud_basic.DTO.requestRegisterLoanDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.DTO.responseLoanDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.ILoan;
import com.sena.crud_basic.interfaces.ILoanDetail;
import com.sena.crud_basic.interfaces.IUsers;
import com.sena.crud_basic.model.loan;
import com.sena.crud_basic.model.loan_detail;
import com.sena.crud_basic.model.users;

import jakarta.persistence.EntityNotFoundException;

@Service
public class loanService {
    @Autowired
    private ILoan _loanData;

    @Autowired
    private IUsers _userData;

    @Autowired
    private IBook _bookData;

    @Autowired
    private ILoanDetail _loanDetail;

    // Obteber todo
    public List<requestRegisterLoanDto> findAllloan() {
        try {
            var loans = _loanData.findAll();
            return MapToList(loans);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos loan" + e);
        }
    }

    // Obtern por join
    public List<responseLoanDto> findAllJoin() {
        return _loanData.findAllJoin();
    }

    // Obtener por nombre
    // public List<loan> findByNameloan(String name) {
    // return _loanData.findByName(name);
    // }

    // Obteber por id
    public requestRegisterLoanDto findloanById(int id) {
        try {
            // Buscar por su ID
            var loan = _loanData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!loan.isPresent()) {
                throw new EntityNotFoundException("loan not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(loan.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching loan with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto saveloan(requestAllLoanDto loanDto) {
        // Validar que el id no exista
        if (_loanData.findById(loanDto.getId_loan()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            loan loan = MapToEntity(loanDto);
            _loanData.save(loan);
            saveRelation(loan, loanDto);
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Guardar relaciones
    public void saveRelation(loan loan, requestAllLoanDto dto) {
        // guardar libro
        
        Integer bookId = dto.getId_book();
        var book = _bookData.findById(bookId)
                .orElseThrow(() -> new RuntimeException("bookId no encontrado con ID: " + bookId));
        var create = new loan_detail(0, loan, book);
        _loanDetail.save(create);

    }

    // Actualizar categoria
    public responseDto updateloan(requestRegisterLoanDto loanDto) {
        try {
            if (loanDto.getId_loan() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _loanData.findById(loanDto.getId_loan());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedloan = MapToEntity(loanDto);
            _loanData.save(updatedloan);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto deleteloan(int id) {
        try {
            var loan = _loanData.findById(id);
            if (loan.isPresent()) {
                _loanData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public loan MapToEntity(requestRegisterLoanDto loanDto) {
        users usersEntity = new users();
        usersEntity.setId_user(loanDto.getId_user());
        return new loan(
                loanDto.getId_loan(),
                LocalDate.now(),
                loanDto.getReturn_date(),
                loanDto.getStatus(),
                usersEntity,
                null);

    }

    //sobrecarga
    public loan MapToEntity(requestAllLoanDto loanDto) {
        users usersEntity = new users();
        usersEntity.setId_user(loanDto.getId_user());
        return new loan(
                loanDto.getId_loan(),
                LocalDate.now(),
                loanDto.getReturn_date(),
                loanDto.getStatus(),
                usersEntity,
                null);

    }

    // Mapeo de Entidad a Dto
    public requestRegisterLoanDto MapToDto(loan entity) {
        return new requestRegisterLoanDto(
                entity.getId_loan(),
                entity.getLoan_date(),
                entity.getReturn_date(),
                entity.getStatus(),
                entity.getUsers().getId_user());
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterLoanDto> MapToList(List<loan> entities) {
        List<requestRegisterLoanDto> dtos = new ArrayList<>();
        for (loan entity : entities) {
            dtos.add(MapToDto(entity));
        }
        return dtos;
    }

    // Response
    public responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
