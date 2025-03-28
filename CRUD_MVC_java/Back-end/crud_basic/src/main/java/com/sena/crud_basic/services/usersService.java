package com.sena.crud_basic.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.interfaces.IUsers;
import com.sena.crud_basic.model.users;

@Service
public class usersService {
    //coneccion con las interfaz
    @Autowired
    private IUsers usersData;

    //Obteber todos los usuarios
    public List<users> findAllUsers(){
        return usersData.findAll();
    }

    //Obteber un usuario por id
    public Optional<users> findByIdUsers(int id){
        return usersData.findById(id);
    }

    //Guardar usuario
    public boolean saveUsers(users user){
        boolean created = false;
        users userSaved = usersData.save(user);
        if(userSaved != null){
            created = true;
        }
        return created;
    }
    //Actualizar usuario
    // public boolean updateUsers(int id,users userUpdate){
    //     boolean updated = false;
    //     var users = findByIdUsers(id);
    //     if (userUpdate.isPresent()) {
    //         updated = true;
            
    //     } 
            
        

    // }


    //Eliminar usuario
    public boolean deleteUsers(int id){
        boolean deleted = false;
        var user = findByIdUsers(id);
        if(user.isPresent()){
            usersData.delete(user.get());
            deleted = true;
        }
        return deleted;
        
    }




}
