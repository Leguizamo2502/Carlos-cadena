package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.book;

@Repository
public interface IBook extends JpaRepository<book, Integer> {
    @Query("SELECT b FROM book b WHERE b.title LIKE %?1%")
    List<book> findByName(String name);

}
