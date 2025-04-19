package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.IBookPublisher;
import com.sena.crud_basic.interfaces.IPusblisher;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_publisher;
import com.sena.crud_basic.model.publisher;

@Service
public class bookPublisherService {
    @Autowired
    private IBookPublisher _bookPublisherData;

    @Autowired
    private IBook _bookData;

    @Autowired
    private IPusblisher _publisherData;

    public List<book_publisher> findAll() {
        return _bookPublisherData.findAll();
    }

    public Optional<book_publisher> findById(int id) {
        return _bookPublisherData.findById(id);
    }

    public responseDto save(requestRegisterBookPublisherDto dto) {
        try {
            Optional<book> book  = _bookData.findById(dto.getId_book());
            Optional<publisher> publisher = _publisherData.findById(dto.getId_publisher());

            if (book.isPresent() && publisher.isPresent()) {
                book_publisher book_publisher = new book_publisher();
                book_publisher.setBook(book.get());
                book_publisher.setPublisher(publisher.get());

                _bookPublisherData.save(book_publisher);
                return createResponse(HttpStatus.CREATED, "Relación book_publisher creada correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "Book o Publisher no encontrado");
                
            }

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar: " + e.getMessage());
        }
    }

    // Update
    public responseDto update(requestRegisterBookPublisherDto dto) {
        if (dto.getId_book_publisher() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        Optional<book_publisher> existing = _bookPublisherData.findById(dto.getId_book_publisher());
        if (existing.isPresent()) {
            try {
                Optional<book> bookOpt = _bookData.findById(dto.getId_book());
                Optional<publisher> publisherOpt = _publisherData.findById(dto.getId_publisher());

                if (bookOpt.isPresent() && publisherOpt.isPresent()) {
                    book_publisher bp = existing.get();
                    bp.setBook(bookOpt.get());
                    bp.setPublisher(publisherOpt.get());

                    _bookPublisherData.save(bp);
                    return createResponse(HttpStatus.OK, "Relación book_publisher actualizada correctamente");
                } else {
                    return createResponse(HttpStatus.NOT_FOUND, "Book o Publisher no encontrado");
                }
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
            }
        }
        return createResponse(HttpStatus.NOT_FOUND,
                "Relación book_publisher no encontrada con ID: " + dto.getId_book_publisher());
    }

    public responseDto delete(int id) {
        var result = findById(id);
        if (result.isPresent()) {
            try {
                _bookPublisherData.deleteById(id);
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
