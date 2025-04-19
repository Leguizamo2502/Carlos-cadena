package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterBookCategoryDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.IBookCategory;
import com.sena.crud_basic.interfaces.ICategory;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_category;
import com.sena.crud_basic.model.category;
import com.sena.crud_basic.model.category;

@Service
public class bookCategoryService {
    @Autowired
    private IBookCategory _bookCategoryData;

    @Autowired
    private IBook _bookData;

    @Autowired
    private ICategory _categoryData;

    public List<book_category> findAll() {
        return _bookCategoryData.findAll();
    }

    public Optional<book_category> findById(int id) {
        return _bookCategoryData.findById(id);
    }

    public responseDto save(requestRegisterBookCategoryDto dto) {
        try {
            Optional<book> book  = _bookData.findById(dto.getId_book());
            Optional<category> category = _categoryData.findById(dto.getId_category());

            if (book.isPresent() && category.isPresent()) {
                book_category book_category = new book_category();
                book_category.setBook(book.get());
                book_category.setCategory(category.get());

                _bookCategoryData.save(book_category);
                return createResponse(HttpStatus.CREATED, "Relación book_category creada correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "Book o category no encontrado");
                
            }

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar: " + e.getMessage());
        }
    }

    // Update
    public responseDto update(requestRegisterBookCategoryDto dto) {
        if (dto.getId_book_category() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        Optional<book_category> existing = _bookCategoryData.findById(dto.getId_book_category());
        if (existing.isPresent()) {
            try {
                Optional<book> bookOpt = _bookData.findById(dto.getId_book());
                Optional<category> categoryOpt = _categoryData.findById(dto.getId_category());

                if (bookOpt.isPresent() && categoryOpt.isPresent()) {
                    book_category bp = existing.get();
                    bp.setBook(bookOpt.get());
                    bp.setCategory(categoryOpt.get());

                    _bookCategoryData.save(bp);
                    return createResponse(HttpStatus.OK, "Relación book_category actualizada correctamente");
                } else {
                    return createResponse(HttpStatus.NOT_FOUND, "Book o category no encontrado");
                }
            } catch (Exception e) {
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
            }
        }
        return createResponse(HttpStatus.NOT_FOUND,
                "Relación book_category no encontrada con ID: " + dto.getId_book_category());
    }

    public responseDto delete(int id) {
        var result = findById(id);
        if (result.isPresent()) {
            try {
                _bookCategoryData.deleteById(id);
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
