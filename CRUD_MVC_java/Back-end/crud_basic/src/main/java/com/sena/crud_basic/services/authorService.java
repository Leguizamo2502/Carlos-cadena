package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterAuthorDto;

import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IAuthor;
import com.sena.crud_basic.model.author;


import jakarta.persistence.EntityNotFoundException;


@Service
public class authorService {

    @Autowired
    private IAuthor _authorData;

    // Obteber todo
    public List<requestRegisterAuthorDto> findAllauthor() {
        try {
            var authors = _authorData.findAll();
            return MapToList(authors);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos author" + e);
        }
    }

    // Obtener por nombre
    public List<author> findByNameauthor(String name) {
        return _authorData.findByName(name);
    }

    // Obteber por id
    public requestRegisterAuthorDto findauthorById(int id) {
        try {
            // Buscar por su ID
            var author = _authorData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!author.isPresent()) {
                throw new EntityNotFoundException("author not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(author.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching author with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto saveauthor(requestRegisterAuthorDto authorDto) {
         // Validar que el id no exista
         if (_authorData.findById(authorDto.getId_author()).isPresent()){
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            _authorData.save(MapToEntity(authorDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updateauthor(requestRegisterAuthorDto authorDto) {
        try {
            if (authorDto.getId_author() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _authorData.findById(authorDto.getId_author());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedauthor = MapToEntity(authorDto);
            _authorData.save(updatedauthor);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deleteauthor(int id) {
        try {
            var author = _authorData.findById(id);
            if (author.isPresent()) {
                _authorData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public author MapToEntity(requestRegisterAuthorDto authorDto) {
        return new author(
                authorDto.getId_author(),
                authorDto.getFirst_name(),
                authorDto.getFirst_name(),
                null);

    }

    // Mapeo de Entidad a Dto
    public requestRegisterAuthorDto MapToDto(author entity) {
        return new requestRegisterAuthorDto(
                entity.getId_author(),
                entity.getFirst_name(),
                entity.getLast_name());
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterAuthorDto> MapToList(List<author> entities) {
        List<requestRegisterAuthorDto> dtos = new ArrayList<>();
        for (author entity : entities) {
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
