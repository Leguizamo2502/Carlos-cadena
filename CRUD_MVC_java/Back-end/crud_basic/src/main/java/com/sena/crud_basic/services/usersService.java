package com.sena.crud_basic.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterUsersDto;

import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IUsers;
import com.sena.crud_basic.model.users;


import jakarta.persistence.EntityNotFoundException;



@Service
public class usersService {
    //Inyeccion
    @Autowired
    private IUsers _usersData;

    // Obteber todo
    public List<requestRegisterUsersDto> findAllusers() {
        try {
            var users = _usersData.findAll();
            return MapToList(users);
        } catch (Exception e) {
            throw new RuntimeException("Error al traer todos users" + e);
        }
    }

    // Obtener por nombre
    public List<users> findByNameusers(String name) {
        return _usersData.findByName(name);
    }

    // Obteber por id
    public requestRegisterUsersDto findusersById(int id) {
        try {
            // Buscar por su ID
            var users = _usersData.findById(id);

            // Si no se encuentraa, lanzamos una excepción
            if (!users.isPresent()) {
                throw new EntityNotFoundException("users not found with ID: " + id);
            }

            // Si se encuentra, la mapeamos a DTO y la retornamos
            return MapToDto(users.get());

        } catch (EntityNotFoundException e) {
            // Excepción específica si no se encuentra 
            throw e; // Aquí puedes decidir si quieres loguear la excepción o no
        } catch (Exception e) {
            // Cualquier otro error, como acceso a datos
            throw new RuntimeException("Error occurred while fetching users with ID: " + id, e);
        }
    }

    // Guardar categoria
    public responseDto saveusers(requestRegisterUsersDto usersDto) {
        try {
            _usersData.save(MapToEntity(usersDto));
            return createResponse(HttpStatus.CREATED, "Se creo correctamenete");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear" + e.getMessage());
        }
    }

    // Actualizar categoria
    public responseDto updateusers(requestRegisterUsersDto usersDto) {
        try {
            if (usersDto.getId_user() <= 0) {
                return createResponse(HttpStatus.BAD_REQUEST, "ID inválido");
            }

            // Verificar existencia
            var existing = _usersData.findById(usersDto.getId_user());
            if (!existing.isPresent()) {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }

            // Mapeo actualizado y guardado
            var updatedusers = MapToEntity(usersDto);
            _usersData.save(updatedusers);
            return createResponse(HttpStatus.OK, "Actualización exitosa");

        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar: " + e.getMessage());
        }
    }


    public responseDto deleteusers(int id) {
        try {
            var users = _usersData.findById(id);
            if (users.isPresent()) {
                _usersData.deleteById(id);
                return createResponse(HttpStatus.OK, "Se borró correctamente");
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "No se encontró el ID");
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar: " + e.getMessage());
        }
    }

    // Mapeo de Dto a modelo 
    public users MapToEntity(requestRegisterUsersDto usersDto) {
        return new users(
                0,
                usersDto.getFirst_name(),
                usersDto.getLast_name(),
                usersDto.getIdentification(),
                usersDto.getEmail(),
                null
                );

    }

    // Mapeo de Entidad a Dto
    public requestRegisterUsersDto MapToDto(users entity) {
        return new requestRegisterUsersDto(
                entity.getId_user(),
                entity.getFirst_name(),
                entity.getLast_name(),
                entity.getIdentification(),
                entity.getEmail()
                );
    }

    // Mapeao de entidad a lista de Dto
    public List<requestRegisterUsersDto> MapToList(List<users> entities) {
        List<requestRegisterUsersDto> dtos = new ArrayList<>();
        for (users entity : entities) {
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
