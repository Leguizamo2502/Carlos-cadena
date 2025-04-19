package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterLoanDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.ILoan;
import com.sena.crud_basic.interfaces.IUsers;
import com.sena.crud_basic.model.loan;
import com.sena.crud_basic.model.users;

@Service
public class loanService {
    @Autowired
    private ILoan _loanData;

    @Autowired
    private IUsers _userData;

    // obtener
    public List<loan> findAllLoan() {
        return _loanData.findAll();
    }

    // obtener por id
    public Optional<loan> findByIdLoan(int id) {
        return _loanData.findById(id);
    }

    // guardar
    public responseDto saveLoan(requestRegisterLoanDto dto) {

        try {
            Optional<users> users = _userData.findById(dto.getId_user());

            if (users.isPresent()) {
                loan loan = new loan();
                loan.setLoan_date(dto.getLoan_date());
                loan.setReturn_date(dto.getReturn_date());
                loan.setStatus(dto.getStatus());
                loan.setUsers(users.get());

                _loanData.save(loan);
                return createResponse(HttpStatus.CREATED, "Prestamo creado correctamente");
            }else{
                return createResponse(HttpStatus.NOT_FOUND, "user para prestamo no encontrado");
            }

            
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el prestamo: " + e.getMessage());
        }
    }

    // Actualizar
    public responseDto updateLoan(requestRegisterLoanDto loanUpdate) {
        if (loanUpdate.getId_user() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        var loan = findByIdLoan(loanUpdate.getId_user());
        if ((loan.isPresent())) {
            try {
                loan.get().setStatus(loanUpdate.getStatus());

                _loanData.save(loan.get());
                return createResponse(HttpStatus.OK, "Prestamo actualizado correctamente");
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el prestamo: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND,
                    "Prestamo no encontrado con ID: " + loanUpdate.getId_loan());
        }
    }

    // Delete
    public responseDto deleteLoan(int id) {
        var loan = findByIdLoan(id);
        if (loan.isPresent()) {
            try {
                _loanData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se elimino correctamente");
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar" + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND, "No se encontro el id del prestamo");
        }

    }

    // Mapeo
    // public loan MapToEntity(requestRegisterLoanDto dto) {
    //     Optional<users> users = _userData.findById(dto.getId_user());
    //     var loan = new loan();

    //     if (users.isPresent()) {
    //         loan.setLoan_date(dto.getLoan_date());
    //         loan.setReturn_date(dto.getReturn_date());
    //         loan.setStatus(dto.getStatus());
    //         loan.setUsers(users.get());
    //         return loan;
    //     }

    // }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
