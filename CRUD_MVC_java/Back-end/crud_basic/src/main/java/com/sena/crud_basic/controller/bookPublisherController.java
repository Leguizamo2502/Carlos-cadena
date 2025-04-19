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

import com.sena.crud_basic.DTO.requestRegisterBookPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.book_publisher;
import com.sena.crud_basic.services.bookPublisherService;

@RestController
@RequestMapping("api/v1/book-publisher")
public class bookPublisherController {

    @Autowired
    private bookPublisherService bookPublisherService;

    @GetMapping("/")
    public ResponseEntity<List<book_publisher>> getAll() {
        return ResponseEntity.ok(bookPublisherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        Optional<book_publisher> result = bookPublisherService.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(404).body("No se encontró la relación con ID: " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterBookPublisherDto dto) {
        responseDto response = bookPublisherService.save(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterBookPublisherDto dto) {
        responseDto response = bookPublisherService.update(dto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        return ResponseEntity.status(bookPublisherService.delete(id).getStatus()).body(bookPublisherService.delete(id));
    }
}
