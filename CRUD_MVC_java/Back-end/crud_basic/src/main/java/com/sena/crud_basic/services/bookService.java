package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookDto;
import com.sena.crud_basic.DTO.requestRegisterBookDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book;

import jakarta.persistence.EntityNotFoundException;

@Service
public class bookService {

    @Autowired
    private IBook _bookData;

    // Obteber todo
    public List<requestRegisterBookDto> findAllbook() {
        try {
            var books = _bookData.findAll();
            return MapToList(books);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos book" + e);
        }
    }

    // Obtener por nombre
    public List<book> findByNamebook(String name) {
        return _bookData.findByName(name);
    }

    // Obteber por id
    public requestRegisterBookDto findbookById(int id) {
        try {
            // Buscar por su ID
            var book = _bookData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!book.isPresent()) {
                throw new EntityNotFoundException("book not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(book.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching book with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto savebook(requestRegisterBookDto bookDto) {
        // Validar que el id no exista
        if (_bookData.findById(bookDto.getId_book()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _bookData.save(MapToEntity(bookDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updatebook(requestRegisterBookDto bookDto) {
        try {
            if (bookDto.getId_book() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _bookData.findById(bookDto.getId_book());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedbook = MapToEntity(bookDto);
            _bookData.save(updatedbook);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto deletebook(int id) {
        try {
            var book = _bookData.findById(id);
            if (book.isPresent()) {
                _bookData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public book MapToEntity(requestRegisterBookDto bookDto) {
        return new book(
                bookDto.getId_book(),
                bookDto.getTitle(),
                bookDto.getDescription(),
                null,
                null,
                null,
                null);

    }

    // Mapeo de Entidad a Dto
    public requestRegisterBookDto MapToDto(book entity) {
        return new requestRegisterBookDto(
                entity.getId_book(),
                entity.getTitle(),
                entity.getDescription());
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterBookDto> MapToList(List<book> entities) {
        List<requestRegisterBookDto> dtos = new ArrayList<>();
        for (book entity : entities) {
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
