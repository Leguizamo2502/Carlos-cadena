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

import com.sena.crud_basic.DTO.requestRegisterAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.authorService;

@RestController
@RequestMapping("api/v1/author")
public class authorController {
    @Autowired
    private authorService _authorService;

    @GetMapping("/")
    public ResponseEntity<Object> findAllauthor(){
        var Listauthor = _authorService.findAllauthor();
        return new ResponseEntity<Object>(Listauthor,HttpStatus.OK);
    }
    //Obtener por nombre
    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> findByNameauthor(@PathVariable String name){
        var Listauthor = _authorService.findByNameauthor(name);
        return new ResponseEntity<Object>(Listauthor,HttpStatus.OK);
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdauthor(@PathVariable int id){
        var author = _authorService.findauthorById(id);
        return new ResponseEntity<>(author,HttpStatus.OK);
    }

    //Guardar
    @PostMapping("/")
    public ResponseEntity<responseDto> saveauthor(@RequestBody requestRegisterAuthorDto authorDto) {
       responseDto response = _authorService.saveauthor(authorDto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    //Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> updateauthor(@RequestBody requestRegisterAuthorDto author) {
        responseDto response = _authorService.updateauthor(author);
        return new ResponseEntity<>(response,response.getStatus());
    }
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteauthor(@PathVariable int id) {
       responseDto response =  _authorService.deleteauthor(id);
        return new ResponseEntity<>(response,response.getStatus());
    }

}
