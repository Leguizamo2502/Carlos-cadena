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

import com.sena.crud_basic.DTO.requestRegisterBookCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.bookCategoryService;

@RestController
@RequestMapping("api/v1/book-category")
public class bookCategoryController {
    @Autowired
    private bookCategoryService _bookCategoryService;

   @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        var Listbookcategory = _bookCategoryService.findAllbookcategory();
        return new ResponseEntity<Object>(Listbookcategory, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        var Listbookcategory = _bookCategoryService.findbookcategoryById(id);
        return new ResponseEntity<Object>(Listbookcategory, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<responseDto> create(@RequestBody requestRegisterBookCategoryDto dto) {
        responseDto response = _bookCategoryService.savebookcategory(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }
    @PutMapping("/")
    public ResponseEntity<responseDto> update(@RequestBody requestRegisterBookCategoryDto dto) {
        responseDto response = _bookCategoryService.updatebookcategory(dto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> delete(@PathVariable int id) {
        responseDto response = _bookCategoryService.deletebookcategory(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
