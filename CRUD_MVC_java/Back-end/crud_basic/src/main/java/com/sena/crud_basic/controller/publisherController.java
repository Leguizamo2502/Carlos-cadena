package com.sena.crud_basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.requestRegisterPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.publisher;
import com.sena.crud_basic.services.PublisherService;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("api/v1/publisher")
public class publisherController {

    @Autowired
    private PublisherService _publisherService;
    
    //Obtener todo
    @GetMapping("/")
    public ResponseEntity<Object> findAllPublisher(){
        var ListPublisher = _publisherService.findAllpublisher();
        return new ResponseEntity<Object>(ListPublisher,HttpStatus.OK);
    }
    //Obtener por nombre
    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> findByNamePublisher(@PathVariable String name){
        var ListPublisher = _publisherService.findByNamepublisher(name);
        return new ResponseEntity<Object>(ListPublisher,HttpStatus.OK);
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdPublisher(@PathVariable int id){
        var publisher = _publisherService.findpublisherById(id);
        return new ResponseEntity<>(publisher,HttpStatus.OK);
    }

    //Guardar
    @PostMapping("/")
    public ResponseEntity<responseDto> savePublisher(@RequestBody requestRegisterPublisherDto publisherDto) {
       responseDto response = _publisherService.savepublisher(publisherDto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    //Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> updatePublisher(@RequestBody requestRegisterPublisherDto publisher) {
        responseDto response = _publisherService.updatepublisher(publisher);
        return new ResponseEntity<>(response,response.getStatus());
    }
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deletePublisher(@PathVariable int id) {
       responseDto response =  _publisherService.deletepublisher(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
