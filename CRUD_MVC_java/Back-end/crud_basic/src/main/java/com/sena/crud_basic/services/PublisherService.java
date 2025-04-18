package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IPusblisher;
import com.sena.crud_basic.model.publisher;

@Service
public class PublisherService {
    @Autowired
    private IPusblisher _pusblisherData;

    // Obtener todo
    public List<publisher> findAllPublisher() {

        return _pusblisherData.findAll();

    }

    // Obtener por nombre
    public List<publisher> findByNamePublisher(String name) {
        return _pusblisherData.findByName(name);
    }

    // Obtener por id
    public Optional<publisher> findByIdPublisher(int id) {
        return _pusblisherData.findById(id);
    }

    // Guardar
    public responseDto savePublisher(requestRegisterPublisherDto publisherDto) {
        try {
            _pusblisherData.save(MapToEntity(publisherDto));
            return createResponse(HttpStatus.CREATED, "Publisher creado correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el publisher: " + e.getMessage());
        }

    }

    // Actualizar
    public responseDto updatePublisher(requestRegisterPublisherDto publisherUpdate) {
        // Validación: ID válido
        if (publisherUpdate.getId_publisher() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        var publisher = findByIdPublisher(publisherUpdate.getId_publisher());

        if (publisher.isPresent()) {
            try {
                publisher.get().setName(publisherUpdate.getName());
                _pusblisherData.save(publisher.get());

                return createResponse(HttpStatus.OK, "Publisher actualizado correctamente");
            } catch (Exception e) {

                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el publisher: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND,
                    "Publisher no encontrado con ID: " + publisherUpdate.getId_publisher());
        }

    }

    // Borrar
    public responseDto deletePublisher(int id) {
        var publisher = findByIdPublisher(id);
        if (publisher.isPresent()) {
            try{
            _pusblisherData.deleteById(id);
            return createResponse(HttpStatus.OK, "Se elimino correctamente");
            }catch(Exception e){
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar"+ e.getMessage());
            }
        } else {

            return createResponse(HttpStatus.NOT_FOUND, "No se encontro el id del publisher");
        }
    }

    // Mapeo
    public publisher MapToEntity(requestRegisterPublisherDto publisherDto) {
        publisher publisher = new publisher();
        publisher.setId_publisher(publisherDto.getId_publisher());
        publisher.setName(publisherDto.getName());
        return publisher;
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }

}
