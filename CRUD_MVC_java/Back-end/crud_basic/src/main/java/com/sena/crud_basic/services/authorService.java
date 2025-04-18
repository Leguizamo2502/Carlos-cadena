package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IAuthor;
import com.sena.crud_basic.model.author;
import com.sena.crud_basic.model.author;

@Service
public class authorService {

    @Autowired
    private IAuthor _authorData;

    // Obtener todo
    public List<author> findAllauthor() {

        return _authorData.findAll();

    }

    // Obtener por nombre
    public List<author> findByNameauthor(String name) {
        return _authorData.findByName(name);
    }

    // Obtener por id
    public Optional<author> findByIdauthor(int id) {
        return _authorData.findById(id);
    }

    // Guardar
    public responseDto saveauthor(requestRegisterAuthorDto authorDto) {
        try {
            _authorData.save(MapToEntity(authorDto));
            return createResponse(HttpStatus.CREATED, "author creado correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el author: " + e.getMessage());
        }

    }

    // Actualizar
    public responseDto updateauthor(requestRegisterAuthorDto authorUpdate) {
        // Validación: ID válido
        if (authorUpdate.getId_author() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        var author = findByIdauthor(authorUpdate.getId_author());

        if (author.isPresent()) {
            try {
                author.get().setFirst_name(authorUpdate.getFirst_name());
                author.get().setLast_name(authorUpdate.getLast_name());
                _authorData.save(author.get());

                return createResponse(HttpStatus.OK, "author actualizado correctamente");
            } catch (Exception e) {

                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el author: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND,
                    "author no encontrado con ID: " + authorUpdate.getId_author());
        }

    }

    // Borrar
    public responseDto deleteauthor(int id) {
        var author = findByIdauthor(id);
        if (author.isPresent()) {
            try{
            _authorData.deleteById(id);
            return createResponse(HttpStatus.OK, "Se elimino correctamente");
            }catch(Exception e){
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar"+ e.getMessage());
            }
        } else {

            return createResponse(HttpStatus.NOT_FOUND, "No se encontro el id del author");
        }
    }

    // Mapeo
    public author MapToEntity(requestRegisterAuthorDto authorDto) {
        author author = new author();
        author.setId_author(authorDto.getId_author());
        author.setFirst_name(authorDto.getFirst_name());
        author.setLast_name(authorDto.getLast_name());
        return author;
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
