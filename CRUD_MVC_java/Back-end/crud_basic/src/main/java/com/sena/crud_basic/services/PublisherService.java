package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterPublisherDto;
import com.sena.crud_basic.DTO.requestRegisterPublisherDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IPusblisher;
import com.sena.crud_basic.model.publisher;


import jakarta.persistence.EntityNotFoundException;

@Service
public class PublisherService {
    @Autowired
    private IPusblisher _pusblisherData;

    // Obteber todo
    public List<requestRegisterPublisherDto> findAllpublisher() {
        try {
            var publishers = _pusblisherData.findAll();
            return MapToList(publishers);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos publisher" + e);
        }
    }

    // Obtener por nombre
    public List<publisher> findByNamepublisher(String name) {
        return _pusblisherData.findByName(name);
    }

    // Obteber por id
    public requestRegisterPublisherDto findpublisherById(int id) {
        try {
            // Buscar por su ID
            var publisher = _pusblisherData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!publisher.isPresent()) {
                throw new EntityNotFoundException("publisher not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(publisher.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching publisher with ID: " + id, e);
        }
    }

    // Guardar 
    public responseDto savepublisher(requestRegisterPublisherDto publisherDto) {
        // Validar que el id no exista
        if (_pusblisherData.findById(publisherDto.getId_publisher()).isPresent()){
            return createResponse(HttpStatus.BAD_REQUEST, "El id ya existe");
        }
        try {
            
            _pusblisherData.save(MapToEntity(publisherDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updatepublisher(requestRegisterPublisherDto publisherDto) {
        try {
            if (publisherDto.getId_publisher() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _pusblisherData.findById(publisherDto.getId_publisher());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedpublisher = MapToEntity(publisherDto);
            _pusblisherData.save(updatedpublisher);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }

    public responseDto deletepublisher(int id) {
        try {
            var publisher = _pusblisherData.findById(id);
            if (publisher.isPresent()) {
                _pusblisherData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo
    public publisher MapToEntity(requestRegisterPublisherDto publisherDto) {
        return new publisher(
                publisherDto.getId_publisher(),
                publisherDto.getName(),
                null);

    }

    // Mapeo de Entidad a Dto
    public requestRegisterPublisherDto MapToDto(publisher entity) {
        return new requestRegisterPublisherDto(
                entity.getId_publisher(),
                entity.getName());
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterPublisherDto> MapToList(List<publisher> entities) {
        List<requestRegisterPublisherDto> dtos = new ArrayList<>();
        for (publisher entity : entities) {
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
