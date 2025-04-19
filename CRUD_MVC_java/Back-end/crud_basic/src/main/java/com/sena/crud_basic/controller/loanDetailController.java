package com.sena.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.requestRegisterLoanDetailDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.loan_detail;

import com.sena.crud_basic.services.loanDetailService;

@RestController
@RequestMapping("api/v1/loan-detail")
public class loanDetailController {
    @Autowired
    private loanDetailService _loanDetail;

    @GetMapping("/")
    public ResponseEntity<List<loan_detail>> getAll() {
        return ResponseEntity.ok(_loanDetail.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<loan_detail> result = _loanDetail.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(404).body("No se encontró la relación con ID: " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterLoanDetailDto dto) {
        responseDto response = _loanDetail.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterLoanDetailDto dto) {
        responseDto response = _loanDetail.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        return ResponseEntity.status(_loanDetail.delete(id).getStatus()).body(_loanDetail.delete(id));
    }
}
