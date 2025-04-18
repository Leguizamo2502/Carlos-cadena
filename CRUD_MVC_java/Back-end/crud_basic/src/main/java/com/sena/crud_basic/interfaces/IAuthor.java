package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.author;

@Repository
public interface IAuthor extends JpaRepository<author,Integer> {

    @Query("SELECT a FROM author a WHERE a.first_name LIKE %?1%")
    List<author> findByName(String name);

}
