package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.requestRegisterUsersDto;
import com.sena.crud_basic.DTO.responseDto;
import com.sena.crud_basic.interfaces.IUsers;
import com.sena.crud_basic.model.users;
import com.sena.crud_basic.model.users;

@Service
public class usersService {
    //Inyeccion
    @Autowired
    private IUsers _usersData;

    // Obtener todo
    public List<users> findAllusers() {

        return _usersData.findAll();

    }

    // Obtener por nombre
    public List<users> findByNameusers(String name) {
        return _usersData.findByName(name);
    }

    // Obtener por id
    public Optional<users> findByIdusers(int id) {
        return _usersData.findById(id);
    }

    // Guardar
    public responseDto saveusers(requestRegisterUsersDto usersDto) {
        try {
            _usersData.save(MapToEntity(usersDto));
            return createResponse(HttpStatus.CREATED, "users creado correctamente");
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el users: " + e.getMessage());
        }

    }

    // Actualizar
    public responseDto updateusers(requestRegisterUsersDto usersUpdate) {
        // Validación: ID válido
        if (usersUpdate.getId_user() <= 0) {
            return createResponse(HttpStatus.BAD_REQUEST, "ID inválido para la actualización");
        }
        var user = findByIdusers(usersUpdate.getId_user());

        if (user.isPresent()) {
            try {
                user.get().setFirst_name(usersUpdate.getFirst_name());
                user.get().setLast_name(usersUpdate.getLast_name());
                user.get().setEmail(usersUpdate.getEmail());
                user.get().setIdentification(usersUpdate.getIdentification());
                _usersData.save(user.get());

                return createResponse(HttpStatus.OK, "users actualizado correctamente");
            } catch (Exception e) {

                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error al actualizar el users: " + e.getMessage());
            }
        } else {
            return createResponse(HttpStatus.NOT_FOUND,
                    "users no encontrado con ID: " + usersUpdate.getId_user());
        }

    }

    // Borrar
    public responseDto deleteusers(int id) {
        var users = findByIdusers(id);
        if (users.isPresent()) {
            try{
            _usersData.deleteById(id);
            return createResponse(HttpStatus.OK, "Se elimino correctamente");
            }catch(Exception e){
                return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar"+ e.getMessage());
            }
        } else {

            return createResponse(HttpStatus.NOT_FOUND, "No se encontro el id del users");
        }
    }

    // Mapeo
    public users MapToEntity(requestRegisterUsersDto usersDto) {
        users users = new users();
        users.setId_user(usersDto.getId_user());
        users.setFirst_name(usersDto.getFirst_name());
        users.setLast_name(usersDto.getLast_name());
        users.setEmail(usersDto.getEmail());
        users.setIdentification(usersDto.getIdentification());
        return users;
    }

    // Response
    private responseDto createResponse(HttpStatus status, String message) {
        responseDto response = new responseDto();
        response.setStatus(status);
        response.setMessage(message);
        return response;
    }


}
