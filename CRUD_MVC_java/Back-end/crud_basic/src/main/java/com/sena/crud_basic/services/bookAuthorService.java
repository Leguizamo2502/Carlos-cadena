package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBookAuthor;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_author;
import com.sena.crud_basic.model.author;

import jakarta.persistence.EntityNotFoundException;

@Service
public class bookAuthorService {

    @Autowired
    private IBookAuthor _bookAuthorData;

    // Obteber todo
    public List<requestRegisterBookAuthorDto> findAllbookauthor() {
        try {
            var bookauthors = _bookAuthorData.findAll();
            return MapToList(bookauthors);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos bookauthor" + e);
        }
    }

    // // Obtener por nombre
    // public List<bookauthor> findByNamebookauthor(String name) {
    //     return _bookAuthorData.findByName(name);
    // }

    // Obteber por id
    public requestRegisterBookAuthorDto findbookauthorById(int id) {
        try {
            // Buscar por su ID
            var bookauthor = _bookAuthorData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!bookauthor.isPresent()) {
                throw new EntityNotFoundException("bookauthor not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(bookauthor.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching bookauthor with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto savebookauthor(requestRegisterBookAuthorDto bookauthorDto) {
        // Validar que el id no exista
        if (_bookAuthorData.findById(bookauthorDto.getId_book_author()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _bookAuthorData.save(MapToEntity(bookauthorDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updatebookauthor(requestRegisterBookAuthorDto bookauthorDto) {
        try {
            if (bookauthorDto.getId_book_author() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _bookAuthorData.findById(bookauthorDto.getId_book_author());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedbookauthor = MapToEntity(bookauthorDto);
            _bookAuthorData.save(updatedbookauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deletebookauthor(int id) {
        try {
            var bookauthor = _bookAuthorData.findById(id);
            if (bookauthor.isPresent()) {
                _bookAuthorData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public book_author MapToEntity(requestRegisterBookAuthorDto dto) {
        book bookEntity = new book();
        bookEntity.setId_book(dto.getId_book());
    
        author authorEntity = new author();
        authorEntity.setId_author(dto.getId_author());
    
        return new book_author(
            dto.getId_book_author(), // usa el ID recibido por si es actualización
            authorEntity,
            bookEntity
        );
    }
    

    // Mapeo de Entidad a Dto
    public requestRegisterBookAuthorDto MapToDto(book_author entity) {
        return new requestRegisterBookAuthorDto(
                entity.getId_book_author(),
                entity.getBook().getId_book(),
                entity.getAuthor().getId_author()
        );
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterBookAuthorDto> MapToList(List<book_author> entities) {
        List<requestRegisterBookAuthorDto> dtos = new ArrayList<>();
        for (book_author entity : entities) {
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
