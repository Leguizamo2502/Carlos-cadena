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

import com.sena.crud_basic.DTO.requestAllLoanDto;
import com.sena.crud_basic.DTO.requestRegisterLoanDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.loanService;

@RestController
@RequestMapping("api/v1/loan")
public class loanController {
    @Autowired
    private loanService _loanService;

    //Obtener todo
    @GetMapping("/")
    public ResponseEntity<Object> findAllloan(){
        var Listloan = _loanService.findAllloan();
        return new ResponseEntity<Object>(Listloan,HttpStatus.OK);
    }

    //Obtener por join
    @GetMapping("/todo")
    public ResponseEntity<Object> findAllJoin(){
        var Listloan = _loanService.findAllJoin();
        return new ResponseEntity<Object>(Listloan,HttpStatus.OK);
    }
    //Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNameloan(@PathVariable String name){
    //     var Listloan = _loanService.findByNameLoan(name);
    //     return new ResponseEntity<Object>(Listloan,HttpStatus.OK);
    // }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdloan(@PathVariable int id){
        var loan = _loanService.findloanById(id);
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    //Guardar
    @PostMapping("/")
    public ResponseEntity<responseDto> saveloan(@RequestBody requestAllLoanDto loanDto) {
       responseDto response = _loanService.saveloan(loanDto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    //Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> updateloan(@RequestBody requestRegisterLoanDto loan) {
        responseDto response = _loanService.updateloan(loan);
        return new ResponseEntity<>(response,response.getStatus());
    }
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteloan(@PathVariable int id) {
       responseDto response =  _loanService.deleteloan(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
