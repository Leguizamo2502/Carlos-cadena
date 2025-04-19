package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookAuthorDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IAuthor;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.IBookAuthor;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_author;
import com.sena.crud_basic.model.author;

@Service
public class bookAuthorService {

    @Autowired
    private IBookAuthor _bookAuthorData;

    @Autowired
    private IBook _bookData;

    @Autowired
    private IAuthor _authorData;

    public List<book_author> findAll() {
        return _bookAuthorData.findAll();
    }

    public Optional<book_author> findById(int id) {
        return _bookAuthorData.findById(id);
    }

    public responseDto save(requestRegisterBookAuthorDto dto) {
        try {
            Optional<book> book  = _bookData.findById(dto.getId_book());
            Optional<author> author = _authorData.findById(dto.getId_author());

            if (book.isPresent() && author.isPresent()) {
                book_author book_author = new book_author();
                book_author.setBook(book.get());
                book_author.setAuthor(author.get());

                _bookAuthorData.save(book_author);
                return createResponse(HttpStatus.CREATED, "Relación book_author creada correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "Book o author no encontrado");
                
            }

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar: " + e.getMessage());
        }
    }

    // Update
    public responseDto update(requestRegisterBookAuthorDto dto) {
        if (dto.getId_book_author() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        Optional<book_author> existing = _bookAuthorData.findById(dto.getId_book_author());
        if (existing.isPresent()) {
            try {
                Optional<book> bookOpt = _bookData.findById(dto.getId_book());
                Optional<author> authorOpt = _authorData.findById(dto.getId_author());

                if (bookOpt.isPresent() && authorOpt.isPresent()) {
                    book_author bp = existing.get();
                    bp.setBook(bookOpt.get());
                    bp.setAuthor(authorOpt.get());

                    _bookAuthorData.save(bp);
                    return createResponse(HttpStatus.OK, "Relación book_author actualizada correctamente");
                } else {
                    return createResponse(HttpStatus.NOT_FOUND, "Book o author no encontrado");
                }
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
            }
        }
        return createResponse(HttpStatus.NOT_FOUND,
                "Relación book_author no encontrada con ID: " + dto.getId_book_author());
    }

    public responseDto delete(int id) {
        var result = findById(id);
        if (result.isPresent()) {
            try {
                _bookAuthorData.deleteById(id);
                return createResponse(HttpStatus.OK, "Eliminado correctamente");
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND, "No se encontró la relación con ID: " + id);
        }
    }

    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }


}
