package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.ICategory;
import com.sena.crud_basic.model.category;

@Service
public class categoryService {

    // Inyecccion de dependencias
    @Autowired
    private ICategory categoryData;

    // Obteber todos los categorias
    public List<category> findAllCategory() {
        return categoryData.findAll();
    }

    //Obtener por nombre
    public List<category> findByNameCategory(String name){
        return categoryData.findByName(name);
    }

    // Obteber un categoria por id
    public Optional<category> findByIdCategory(int id) {
        return categoryData.findById(id);
    }

    // Guardar categoria
    public responseDto saveCategory(requestRegisterCategoryDto categoryDto) {
        try {
            categoryData.save(MapToEnity(categoryDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updateCategory(requestRegisterCategoryDto categoryUpdate) {
        if (categoryUpdate.getId_category() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "Id invalido");
        }
        var category = findByIdCategory(categoryUpdate.getId_category());
        if (category.isPresent()) {
            try {
                category.get().setName(categoryUpdate.getName());
                categoryData.save(category.get());
                return createResponse(HttpStatus.OK,"Se actualizo correctramente");
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar" + e.getMessage());

            }
        }
        else{
            return createResponse(HttpStatus.NOT_FOUND,"No se encontro el Id");
        }

    }

    public responseDto deleteCategory(int id) {
        var category = findByIdCategory(id);
        if (category.isPresent()) {
            try{
                categoryData.deleteById(id);
                return createResponse(HttpStatus.OK,"Se borro correctamente");
            }catch(Exception e){
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Error al crear"+ e.getMessage());
            }
        }else{
            return createResponse(HttpStatus.NOT_FOUND,"No se encontro el id");
        }

    }

    // Mapeo de Dto a modelo categoria
    public category MapToEnity(requestRegisterCategoryDto categoryDto) {
        return new category(
                categoryDto.getId_category(),
                categoryDto.getName(),
                null);
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}