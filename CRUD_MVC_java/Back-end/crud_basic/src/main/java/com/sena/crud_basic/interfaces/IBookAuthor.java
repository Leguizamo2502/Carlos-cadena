package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.book_author;

@Repository
public interface IBookAuthor extends JpaRepository<book_author, Integer> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
