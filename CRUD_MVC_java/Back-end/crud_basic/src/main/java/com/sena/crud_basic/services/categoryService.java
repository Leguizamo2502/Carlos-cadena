package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.ICategory;
import com.sena.crud_basic.model.category;

import jakarta.persistence.EntityNotFoundException;

@Service
public class categoryService {

    // Inyecccion de dependencias
    @Autowired
    private ICategory _categoryData;

    // Obteber todos los categorias
    public List<requestRegisterCategoryDto> findAllCategory() {
        try {
            var categorys = _categoryData.findAll();
            return MapToList(categorys);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos category" + e);
        }
    }

    // Obtener por nombre
    public List<category> findByNameCategory(String name) {
        return _categoryData.findByName(name);
    }

    // Obteber un categoria por id
    public requestRegisterCategoryDto findCategoryById(int id) {
        try {
            // Buscar la categoría por su ID
            var category = _categoryData.findById(id);

            // Si no se encuentra la categoría, lanzamos una excepción
            if (!category.isPresent()) {
                throw new EntityNotFoundException("Category not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(category.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra la categoría
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching category with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto saveCategory(requestRegisterCategoryDto categoryDto) {
        // Validar que el id no exista
        if (_categoryData.findById(categoryDto.getId_category()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _categoryData.save(MapToEntity(categoryDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updateCategory(requestRegisterCategoryDto categoryDto) {
        try {
            if (categoryDto.getId_category() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _categoryData.findById(categoryDto.getId_category());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedCategory = MapToEntity(categoryDto);
            _categoryData.save(updatedCategory);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    // if (existing != null) {
    // try {

    // } catch (Exception e) {
    // return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar"
    // + e.getMessage());

    // }
    // } else {
    // return createResponse(HttpStatus.NOT_FOUND, "No se encontro el Id");
    // }

    public responseDto deleteCategory(int id) {
        try {
            var category = _categoryData.findById(id);
            if (category.isPresent()) {
                _categoryData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo categoria
    public category MapToEntity(requestRegisterCategoryDto categoryDto) {
        return new category(
                categoryDto.getId_category(),
                categoryDto.getName(),
                null
            );
    }

    // Mapeo de Entidad a Dto
    public requestRegisterCategoryDto MapToDto(category entity) {
        return new requestRegisterCategoryDto(
                entity.getId_category(),
                entity.getName()
            );
            
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterCategoryDto> MapToList(List<category> entities) {
        List<requestRegisterCategoryDto> dtos = new ArrayList<>();
        for (category entity : entities) {
            dtos.add(MapToDto(entity));
        }
        return dtos;
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}