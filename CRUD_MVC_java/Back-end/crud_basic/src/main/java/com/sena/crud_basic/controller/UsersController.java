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

import com.sena.crud_basic.DTO.requestRegisterUsersDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.services.usersService;

@RestController
@RequestMapping("api/v1/user")
public class UsersController {
    @Autowired
    private usersService _usersService;
    
    //Obtener todo
    @GetMapping("/")
    public ResponseEntity<Object> findAllusers(){
        var Listusers = _usersService.findAllusers();
        return new ResponseEntity<Object>(Listusers,HttpStatus.OK);
    }
    //Obtener por nombre
    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> findByNameusers(@PathVariable String name){
        var Listusers = _usersService.findByNameusers(name);
        return new ResponseEntity<Object>(Listusers,HttpStatus.OK);
    }

    //Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdusers(@PathVariable int id){
        var users = _usersService.findusersById(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    //Guardar
    @PostMapping("/")
    public ResponseEntity<responseDto> saveusers(@RequestBody requestRegisterUsersDto usersDto) {
       responseDto response = _usersService.saveusers(usersDto);
        return new ResponseEntity<>(response,response.getStatus());
    }

    //Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> updateusers(@RequestBody requestRegisterUsersDto users) {
        responseDto response = _usersService.updateusers(users);
        return new ResponseEntity<>(response,response.getStatus());
    }
    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteusers(@PathVariable int id) {
       responseDto response =  _usersService.deleteusers(id);
        return new ResponseEntity<>(response,response.getStatus());
    }
}
