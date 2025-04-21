package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.sena.crud_basic.services.loanDetailService;

@RestController
@RequestMapping("api/v1/loan-detail")
public class loanDetailController {
    @Autowired
    private loanDetailService _loanDetail;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        var ListloanDetail = _loanDetail.findAllloanDetail();
        return new ResponseEntity<Object>(ListloanDetail, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var ListloanDetail = _loanDetail.findloanDetailById(id);
        return new ResponseEntity<Object>(ListloanDetail, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterLoanDetailDto dto) {
        responseDto response = _loanDetail.saveloanDetail(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterLoanDetailDto dto) {
        responseDto response = _loanDetail.updateloanDetail(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = _loanDetail.deleteloanDetail(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
