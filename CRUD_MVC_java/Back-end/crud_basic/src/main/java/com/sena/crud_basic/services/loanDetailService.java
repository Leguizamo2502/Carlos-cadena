package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterLoanDetailDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.ILoanDetail;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.loan_detail;
import com.sena.crud_basic.model.loan;
import jakarta.persistence.EntityNotFoundException;

@Service
public class loanDetailService {
    @Autowired
    private ILoanDetail _loanDetailData;

    // Obteber todo
    public List<requestRegisterLoanDetailDto> findAllloanDetail() {
        try {
            var loanDetails = _loanDetailData.findAll();
            return MapToList(loanDetails);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos loanDetail" + e);
        }
    }

    // // Obtener por nombre
    // public List<loanDetail> findByNameloanDetail(String name) {
    //     return _loanDetailData.findByName(name);
    // }

    // Obteber por id
    public requestRegisterLoanDetailDto findloanDetailById(int id) {
        try {
            // Buscar por su ID
            var loanDetail = _loanDetailData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!loanDetail.isPresent()) {
                throw new EntityNotFoundException("loanDetail not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(loanDetail.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching loanDetail with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto saveloanDetail(requestRegisterLoanDetailDto loanDetailDto) {
        if (_loanDetailData.findById(loanDetailDto.getId_loan_detail()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _loanDetailData.save(MapToEntity(loanDetailDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updateloanDetail(requestRegisterLoanDetailDto loanDetailDto) {
        try {
            if (loanDetailDto.getId_loan_detail() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _loanDetailData.findById(loanDetailDto.getId_loan_detail());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedloanDetail = MapToEntity(loanDetailDto);
            _loanDetailData.save(updatedloanDetail);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deleteloanDetail(int id) {
        try {
            var loanDetail = _loanDetailData.findById(id);
            if (loanDetail.isPresent()) {
                _loanDetailData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public loan_detail MapToEntity(requestRegisterLoanDetailDto dto) {
        book bookEntity = new book();
        bookEntity.setId_book(dto.getId_book());
    
        loan loanEntity = new loan();
        loanEntity.setId_loan(dto.getId_loan());
    
        return new loan_detail(
            dto.getId_loan_detail(), 
            // dto.getQuantity(),
            loanEntity,
            bookEntity
        );
    }
    

    // Mapeo de Entidad a Dto
    public requestRegisterLoanDetailDto MapToDto(loan_detail entity) {
        return new requestRegisterLoanDetailDto(
                entity.getId_loan_detail(),
                entity.getLoan().getId_loan(),
                entity.getBook().getId_book()
        );
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterLoanDetailDto> MapToList(List<loan_detail> entities) {
        List<requestRegisterLoanDetailDto> dtos = new ArrayList<>();
        for (loan_detail entity : entities) {
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
