package com.sena.crud_basic.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.book_category;

@Repository
public interface IBookCategory extends JpaRepository<book_category, Integer> {
    // Custom query methods can be defined here if needed

}
