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

import com.sena.crud_basic.DTO.requestRegisterBookAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.bookAuthorService;

@RestController
@RequestMapping("api/v1/book-author")
public class bookAuthorController {

    @Autowired
    private bookAuthorService _bookAuthorService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        var Listbookauthor = _bookAuthorService.findAllbookauthor();
        return new ResponseEntity<Object>(Listbookauthor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var Listbookauthor = _bookAuthorService.findbookauthorById(id);
        return new ResponseEntity<Object>(Listbookauthor, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterBookAuthorDto dto) {
        responseDto response = _bookAuthorService.savebookauthor(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterBookAuthorDto dto) {
        responseDto response = _bookAuthorService.updatebookauthor(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = _bookAuthorService.deletebookauthor(id);
        return new ResponseEntity<>(response,response.getStatus());
    }


}
