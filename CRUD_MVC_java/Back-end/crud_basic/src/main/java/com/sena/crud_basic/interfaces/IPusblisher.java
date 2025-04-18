package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.publisher;

@Repository
public interface IPusblisher extends JpaRepository<publisher,Integer> {

    @Query("SELECT p FROM publisher p WHERE p.name LIKE %?1%")
    List<publisher> findByName(String name);


}
