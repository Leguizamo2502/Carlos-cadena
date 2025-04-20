package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookPublisherDto;
import com.sena.crud_basic.DTO.requestRegisterBookPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.IBookPublisher;
import com.sena.crud_basic.interfaces.IPusblisher;
import com.sena.crud_basic.model.book_publisher;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_publisher;
import com.sena.crud_basic.model.publisher;

import jakarta.persistence.EntityNotFoundException;

@Service
public class bookPublisherService {
    @Autowired
    private IBookPublisher _bookPublisherData;


    // Obteber todo
    public List<requestRegisterBookPublisherDto> findAllbookPublisher() {
        try {
            var bookPublishers = _bookPublisherData.findAll();
            return MapToList(bookPublishers);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos bookPublisher" + e);
        }
    }

    // // Obtener por nombre
    // public List<bookPublisher> findByNamebookPublisher(String name) {
    //     return _bookPublisherData.findByName(name);
    // }

    // Obteber por id
    public requestRegisterBookPublisherDto findbookPublisherById(int id) {
        try {
            // Buscar por su ID
            var bookPublisher = _bookPublisherData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!bookPublisher.isPresent()) {
                throw new EntityNotFoundException("bookPublisher not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(bookPublisher.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching bookPublisher with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto savebookPublisher(requestRegisterBookPublisherDto bookPublisherDto) {
        
        try {
            _bookPublisherData.save(MapToEntity(bookPublisherDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updatebookPublisher(requestRegisterBookPublisherDto bookPublisherDto) {
        try {
            if (bookPublisherDto.getId_book_publisher() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _bookPublisherData.findById(bookPublisherDto.getId_book_publisher());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedbookPublisher = MapToEntity(bookPublisherDto);
            _bookPublisherData.save(updatedbookPublisher);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deletebookPublisher(int id) {
        try {
            var bookPublisher = _bookPublisherData.findById(id);
            if (bookPublisher.isPresent()) {
                _bookPublisherData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public book_publisher MapToEntity(requestRegisterBookPublisherDto dto) {
        book bookEntity = new book();
        bookEntity.setId_book(dto.getId_book());
    
        publisher publisherEntity = new publisher();
        publisherEntity.setId_publisher(dto.getId_publisher());
    
        return new book_publisher(
            dto.getId_book_publisher(), // usa el ID recibido por si es actualización
            publisherEntity,
            bookEntity
        );
    }
    

    // Mapeo de Entidad a Dto
    public requestRegisterBookPublisherDto MapToDto(book_publisher entity) {
        return new requestRegisterBookPublisherDto(
                entity.getId_book_publisher(),
                entity.getBook().getId_book(),
                entity.getPublisher().getId_publisher()
        );
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterBookPublisherDto> MapToList(List<book_publisher> entities) {
        List<requestRegisterBookPublisherDto> dtos = new ArrayList<>();
        for (book_publisher entity : entities) {
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
