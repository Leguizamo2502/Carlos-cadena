package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestAllBookDto;
import com.sena.crud_basic.DTO.requestRegisterBookDto;
import com.sena.crud_basic.DTO.responseBookDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IAuthor;
import com.sena.crud_basic.interfaces.IBook;
import com.sena.crud_basic.interfaces.IBookAuthor;
import com.sena.crud_basic.interfaces.IBookCategory;
import com.sena.crud_basic.interfaces.IBookPublisher;
import com.sena.crud_basic.interfaces.ICategory;
import com.sena.crud_basic.interfaces.IPusblisher;
import com.sena.crud_basic.model.book;
import com.sena.crud_basic.model.book_author;
import com.sena.crud_basic.model.book_category;
import com.sena.crud_basic.model.book_publisher;

import jakarta.persistence.EntityNotFoundException;

@Service
public class bookService {

    @Autowired
    private IBook _bookData;

    @Autowired 
    private ICategory _categoryData;
    @Autowired
    private IBookCategory _bookCategory;

    @Autowired 
    private IPusblisher _publisherData;
    @Autowired
    private IBookPublisher _bookPublisher;

    @Autowired
    private IAuthor _authorData;
    @Autowired
    private IBookAuthor _bookAuthor;
    

    public List<responseBookDto> getBooksWithAllRelations() {
        List<Object[]> results = _bookData.findBooksWithAllRelations();
        
        // Usamos un mapa para combinar resultados por ID de libro
        Map<Integer, responseBookDto> bookMap = new HashMap<>();
        
        for (Object[] result : results) {
            Integer idBook = ((Number) result[0]).intValue();
            String title = (String) result[1];
            String description = (String) result[2];
            String publisherName = (String) result[3];
            String authorName = (String) result[4];
            String categoryName = (String) result[5];
            
            // Obtener o crear DTO para este libro
            responseBookDto dto = bookMap.computeIfAbsent(idBook, id -> {
                responseBookDto newDto = new responseBookDto();
                newDto.setId_book(idBook);
                newDto.setTitle(title);
                newDto.setDescription(description);
                newDto.setPublishers(new ArrayList<>());
                newDto.setAuthors(new ArrayList<>());
                newDto.setCategories(new ArrayList<>());
                return newDto;
            });
            
            // Añadir relaciones si no son null y no están ya en las listas
            if (publisherName != null && !dto.getPublishers().contains(publisherName)) {
                dto.getPublishers().add(publisherName);
            }
            
            if (authorName != null && !dto.getAuthors().contains(authorName)) {
                dto.getAuthors().add(authorName);
            }
            
            if (categoryName != null && !dto.getCategories().contains(categoryName)) {
                dto.getCategories().add(categoryName);
            }
        }
        
        return new ArrayList<>(bookMap.values());
    }

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
    public List<String> findByNamebook(String name) {
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
    public responseDto savebook(requestAllBookDto bookDto) {
        // Validar que el id no exista
        if (_bookData.findById(bookDto.getId_book()).isPresent()) {
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            book book = MapToEntity(bookDto);
            _bookData.save(book);
            saveRelation(book, bookDto);
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }


    //Gyardar relaciones
    public void saveRelation(book book,requestAllBookDto dto){
        //Guardar Categorias
        List<book_category> relacionesCategoria = new ArrayList<>();
        for(Integer categoryId: dto.getId_category()){
            var category = _categoryData.findById(categoryId)
                            .orElseThrow(() -> new RuntimeException("categoryId no encontrado con ID: " + categoryId));
            relacionesCategoria.add(new book_category(0, category, book));
        }
        _bookCategory.saveAll(relacionesCategoria);

        //Guardar AUtor
        List<book_author> relacionAuthor = new ArrayList<>();
        for(Integer authorId:dto.getId_author()){
            var author = _authorData.findById(authorId)
            .orElseThrow(() -> new RuntimeException("authorId no encontrado con ID: " + authorId));
            relacionAuthor.add(new book_author(0, author,book));
        }
        _bookAuthor.saveAll(relacionAuthor);

        //Guardar publisher
        List<book_publisher> relacionPublisher = new ArrayList<>();
        for(Integer publisherId: dto.getId_publisher()){
            var publisher = _publisherData.findById(publisherId)
            .orElseThrow(() -> new RuntimeException("authorId no encontrado con ID: " + publisherId));
            relacionPublisher.add(new book_publisher(0, publisher, book));
        }
        _bookPublisher.saveAll(relacionPublisher);



    }

    // Actualizar categoria
    public responseDto updatebook(int id,requestRegisterBookDto bookDto) {
        try {
            if (id <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _bookData.findById(id);
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            bookDto.setId_book(id);
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

    //sobercarga
    public book MapToEntity(requestAllBookDto bookDto) {
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
