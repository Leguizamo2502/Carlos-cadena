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

import com.sena.crud_basic.DTO.requestAllBookDto;
import com.sena.crud_basic.DTO.requestRegisterBookDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.bookService;

@RestController
@RequestMapping("api/v1/book")
public class bookController {
    @Autowired
    private bookService _bookService;

    // Obtener todo
    @GetMapping("/")
    public ResponseEntity<Object> findAllbook() {
        var Listbook = _bookService.findAllbook();
        return new ResponseEntity<Object>(Listbook, HttpStatus.OK);
    }

    // TRaer con todo
    @GetMapping("/todo")
    public ResponseEntity<Object> findJoin() {
        var Listbook = _bookService.getBooksWithAllRelations();
        return new ResponseEntity<Object>(Listbook, HttpStatus.OK);
    }

    //Traer por categoria
    @GetMapping("/filter/{id}")
    public ResponseEntity<Object> filter(@PathVariable int id) {
        var Listbook = _bookService.getFilter(id);
        return new ResponseEntity<Object>(Listbook, HttpStatus.OK);
    }

    // Obtener por nombre
    // @GetMapping("/filter/{name}")
    // public ResponseEntity<Object> findByNamebook(@PathVariable String name) {
    //     var Listbook = _bookService.findByNamebook(name);
    //     return new ResponseEntity<>(Listbook, HttpStatus.OK);
    // }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdbook(@PathVariable int id) {
        var book = _bookService.findbookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    // Guardar
    @PostMapping("/")
    public ResponseEntity<responseDto> savebook(@RequestBody requestAllBookDto bookDto) {
        responseDto response = _bookService.savebook(bookDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<responseDto> updatebook(@PathVariable int id,@RequestBody requestRegisterBookDto book) {
        responseDto response = _bookService.updatebook(id,book);
        return new ResponseEntity<>(response, response.getStatus());
    }

    // Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deletebook(@PathVariable int id) {
        responseDto response = _bookService.deletebook(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
