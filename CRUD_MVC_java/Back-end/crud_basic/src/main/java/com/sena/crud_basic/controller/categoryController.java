package com.sena.crud_basic.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.requestRegisterCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.model.category;
import com.sena.crud_basic.services.categoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("api/v1/category")
public class categoryController {

    /*
     * GET =consulta
     * POST= crear
     * PUT= actualizar
     * PATCH= actualizar parcial
     * DELETE= eliminar
     */
    @Autowired
    private categoryService _categoryService;    //Obteber todos los categorias
    


    //Obtener todo
    @GetMapping("/")
    public ResponseEntity<Object> findAllCategory(){
        var ListCategory = _categoryService.findAllCategory();
        return new ResponseEntity<Object>(ListCategory,HttpStatus.OK);
    }

    //Obtener por nombre
    @GetMapping("/filter/{name}")
    public ResponseEntity<Object> findByNameCategory(@PathVariable String name){
        var ListCategory = _categoryService.findByNameCategory(name);
        return new ResponseEntity<Object>(ListCategory,HttpStatus.OK);
    } 

    //Obtener por Id
    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdCategory(@PathVariable int id){
        var category = _categoryService.findCategoryById(id);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    //Crear
    @PostMapping("/")
    public ResponseEntity<responseDto> saveCategory(@RequestBody requestRegisterCategoryDto categoryDto) {
        responseDto response = _categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    //Actualizar
    @PutMapping("/")
    public ResponseEntity<responseDto> putMethodName(@RequestBody requestRegisterCategoryDto category) {
        responseDto response = _categoryService.updateCategory(category);
        return new ResponseEntity<>(response, response.getStatus());
    }

    //Borrar
    @DeleteMapping("/{id}")
    public ResponseEntity<responseDto> deleteCategory(@PathVariable int id){
        responseDto response = _categoryService.deleteCategory(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
    
    

     
}
