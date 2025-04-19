package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterLoanDetailDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.ILoan;
import com.sena.crud_basic.interfaces.ILoanDetail;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.loan_detail;
import com.sena.crud_basic.model.loan_detail;
import com.sena.crud_basic.model.loan;

@Service
public class loanDetailService {
    @Autowired
    private ILoanDetail _loanDetailData;

    @Autowired
    private ILoan _loanData;

    @Autowired
    private IBook _bookData;

    public List<loan_detail> findAll() {
        return _loanDetailData.findAll();
    }

    public Optional<loan_detail> findById(int id) {
        return _loanDetailData.findById(id);
    }

    public responseDto save(requestRegisterLoanDetailDto dto) {
        try {
            Optional<book> book  = _bookData.findById(dto.getId_book());
            Optional<loan> loan = _loanData.findById(dto.getId_loan());

            if (book.isPresent() && loan.isPresent()) {
                loan_detail loan_detail = new loan_detail();
                loan_detail.setBook(book.get());
                loan_detail.setLoan(loan.get());
                loan_detail.setQuantity(dto.getQuantity());

                _loanDetailData.save(loan_detail);
                return createResponse(HttpStatus.CREATED, "Relación loan_detail creada correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "Book o loan no encontrado");
                
            }

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar: " + e.getMessage());
        }
    }

    // Update
    public responseDto update(requestRegisterLoanDetailDto dto) {
        if (dto.getId_loan_detail() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        Optional<loan_detail> existing = _loanDetailData.findById(dto.getId_loan_detail());
        if (existing.isPresent()) {
            try {
                Optional<book> bookOpt = _bookData.findById(dto.getId_book());
                Optional<loan> loanOpt = _loanData.findById(dto.getId_loan());

                if (bookOpt.isPresent() && loanOpt.isPresent()) {
                    loan_detail bp = existing.get();
                    bp.setBook(bookOpt.get());
                    bp.setLoan(loanOpt.get());
                    bp.setQuantity(dto.getQuantity());

                    _loanDetailData.save(bp);
                    return createResponse(HttpStatus.OK, "Relación loan_detail actualizada correctamente");
                } else {
                    return createResponse(HttpStatus.NOT_FOUND, "Book o loan no encontrado");
                }
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
            }
        }
        return createResponse(HttpStatus.NOT_FOUND,
                "Relación loan_detail no encontrada con ID: " + dto.getId_loan_detail());
    }

    public responseDto delete(int id) {
        var result = findById(id);
        if (result.isPresent()) {
            try {
                _loanDetailData.deleteById(id);
                return createResponse(HttpStatus.OK, "Eliminado correctamente");
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND, "No se encontró la relación con ID: " + id);
        }
    }

    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
