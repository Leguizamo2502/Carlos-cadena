package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.model.book;

@Service
public class bookService {

    @Autowired
    private IBook _bookData;

    // Obtener todo
    public List<book> findAllbook() {

        return _bookData.findAll();

    }

    // Obtener por nombre
    public List<book> findByNamebook(String name) {
        return _bookData.findByName(name);
    }

    // Obtener por id
    public Optional<book> findByIdbook(int id) {
        return _bookData.findById(id);
    }

    // Guardar
    public responseDto savebook(requestRegisterBookDto bookDto) {
        try {
            _bookData.save(MapToEntity(bookDto));
            return createResponse(HttpStatus.CREATED, "book creado correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el book: " + e.getMessage());
        }

    }

    // Actualizar
    public responseDto updatebook(requestRegisterBookDto bookUpdate) {
        // Validación: ID válido
        if (bookUpdate.getId_book() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        var book = findByIdbook(bookUpdate.getId_book());

        if (book.isPresent()) {
            try {
                book.get().setTitle(bookUpdate.getTitle());
                book.get().setDescription(bookUpdate.getDescription());
                _bookData.save(book.get());

                return createResponse(HttpStatus.OK, "book actualizado correctamente");
            } catch (Exception e) {

                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el book: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND,
                    "book no encontrado con ID: " + bookUpdate.getId_book());
        }

    }

    // Borrar
    public responseDto deletebook(int id) {
        var book = findByIdbook(id);
        if (book.isPresent()) {
            try{
            _bookData.deleteById(id);
            return createResponse(HttpStatus.OK, "Se elimino correctamente");
            }catch(Exception e){
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar"+ e.getMessage());
            }
        } else {

            return createResponse(HttpStatus.NOT_FOUND, "No se encontro el id del book");
        }
    }

    // Mapeo
    public book MapToEntity(requestRegisterBookDto bookDto) {
        book book = new book();
        book.setId_book(bookDto.getId_book());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }
}
