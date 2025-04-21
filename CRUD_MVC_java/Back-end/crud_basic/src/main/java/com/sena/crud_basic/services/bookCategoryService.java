package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBookCategory;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_category;
import com.sena.crud_basic.model.category;
import jakarta.persistence.EntityNotFoundException;

@Service
public class bookCategoryService {
    @Autowired
    private IBookCategory _bookCategoryData;
// Obteber todo
    public List<requestRegisterBookCategoryDto> findAllbookcategory() {
        try {
            var bookcategorys = _bookCategoryData.findAll();
            return MapToList(bookcategorys);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos bookcategory" + e);
        }
    }

    // // Obtener por nombre
    // public List<bookcategory> findByNamebookcategory(String name) {
    //     return _bookCategoryData.findByName(name);
    // }

    // Obteber por id
    public requestRegisterBookCategoryDto findbookcategoryById(int id) {
        try {
            // Buscar por su ID
            var bookcategory = _bookCategoryData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!bookcategory.isPresent()) {
                throw new EntityNotFoundException("bookcategory not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(bookcategory.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching bookcategory with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto savebookcategory(requestRegisterBookCategoryDto bookcategoryDto) {
        if (_bookCategoryData.findById(bookcategoryDto.getId_book_category()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _bookCategoryData.save(MapToEntity(bookcategoryDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updatebookcategory(requestRegisterBookCategoryDto bookcategoryDto) {
        try {
            if (bookcategoryDto.getId_book_category() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _bookCategoryData.findById(bookcategoryDto.getId_book_category());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedbookcategory = MapToEntity(bookcategoryDto);
            _bookCategoryData.save(updatedbookcategory);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deletebookcategory(int id) {
        try {
            var bookcategory = _bookCategoryData.findById(id);
            if (bookcategory.isPresent()) {
                _bookCategoryData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public book_category MapToEntity(requestRegisterBookCategoryDto dto) {
        book bookEntity = new book();
        bookEntity.setId_book(dto.getId_book());
    
        category categoryEntity = new category();
        categoryEntity.setId_category(dto.getId_category());
    
        return new book_category(
            dto.getId_book_category(), // usa el ID recibido por si es actualización
            categoryEntity,
            bookEntity
        );
    }
    

    // Mapeo de Entidad a Dto
    public requestRegisterBookCategoryDto MapToDto(book_category entity) {
        return new requestRegisterBookCategoryDto(
                entity.getId_book_category(),
                entity.getBook().getId_book(),
                entity.getCategory().getId_category()
        );
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterBookCategoryDto> MapToList(List<book_category> entities) {
        List<requestRegisterBookCategoryDto> dtos = new ArrayList<>();
        for (book_category entity : entities) {
            dtos.add(MapToDto(entity));
        }
        return dtos;
    }

    // Response
    public responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
