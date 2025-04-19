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

import com.sena.crud_basic.DTO.requestRegisterBookAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.book_author;
import com.sena.crud_basic.services.bookAuthorService;

@RestController
@RequestMapping("api/v1/book-author")
public class bookAuthorController {

    @Autowired
    private bookAuthorService _bookAuthorService;

    @GetMapping("/")
    public ResponseEntity<List<book_author>> getAll() {
        return ResponseEntity.ok(_bookAuthorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<book_author> result = _bookAuthorService.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(404).body("No se encontró la relación con ID: " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterBookAuthorDto dto) {
        responseDto response = _bookAuthorService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterBookAuthorDto dto) {
        responseDto response = _bookAuthorService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        return ResponseEntity.status(_bookAuthorService.delete(id).getStatus()).body(_bookAuthorService.delete(id));
    }


}
