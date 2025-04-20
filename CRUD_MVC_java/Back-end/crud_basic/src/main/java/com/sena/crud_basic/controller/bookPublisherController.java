package com.sena.crud_basic.controller;

import java.util.List;
import java.util.Optional;

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

import com.sena.crud_basic.DTO.requestRegisterBookPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.book_publisher;
import com.sena.crud_basic.services.bookPublisherService;

@RestController
@RequestMapping("api/v1/book-publisher")
public class bookPublisherController {

    @Autowired
    private bookPublisherService _bookPublisherService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        var Listbookpublisher = _bookPublisherService.findAllbookPublisher();
        return new ResponseEntity<Object>(Listbookpublisher, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var Listbookpublisher = _bookPublisherService.findbookPublisherById(id);
        return new ResponseEntity<Object>(Listbookpublisher, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterBookPublisherDto dto) {
        responseDto response = _bookPublisherService.savebookPublisher(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterBookPublisherDto dto) {
        responseDto response = _bookPublisherService.updatebookPublisher(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = _bookPublisherService.deletebookPublisher(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
