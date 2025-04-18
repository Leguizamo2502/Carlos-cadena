package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.users;

@Repository
public interface IUsers extends JpaRepository<users, Integer>{
// me da pa una crud basica, no personalizada
    @Query("SELECT u FROM users u WHERE u.first_name LIKE %?1%")
    List<users> findByName(String name);
}
