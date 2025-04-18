package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.category;

@Repository
public interface ICategory extends JpaRepository<category,Integer> {
   @Query("SELECT c FROM category c WHERE c.name LIKE %?1%")
   List<category> findByName(String name);
}
