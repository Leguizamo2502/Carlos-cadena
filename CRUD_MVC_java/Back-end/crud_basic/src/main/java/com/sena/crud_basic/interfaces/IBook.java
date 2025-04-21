package com.sena.crud_basic.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.book;

@Repository
public interface IBook extends JpaRepository<book, Integer> {
    // @Query("SELECT b.title FROM book b WHERE b.title LIKE %?1%")
    // List<String> findByName(String name);

    // @Query(value = "SELECT b.id_book, b.title, b.description, p.name AS
    // publisherName " +
    // "FROM book b " +
    // "INNER JOIN book_publisher bp ON b.id_book = bp.id_book " +
    // "INNER JOIN publisher p ON bp.id_publisher = p.id_publisher",
    // nativeQuery = true)
    // List<Object[]> findBooksWithPublishers();
    @Query(value = "SELECT DISTINCT b.id_book, b.title, b.description, " +
            "p.name AS publisherName, a.first_name AS authorName, c.name AS categoryName " +
            "FROM book b " +
            "LEFT JOIN book_publisher bp ON b.id_book = bp.id_book " +
            "LEFT JOIN publisher p ON bp.id_publisher = p.id_publisher " +
            "LEFT JOIN book_author ba ON b.id_book = ba.id_book " +
            "LEFT JOIN author a ON ba.id_author = a.id_author " +
            "LEFT JOIN book_category bc ON b.id_book = bc.id_book " +
            "LEFT JOIN category c ON bc.id_category = c.id_category", nativeQuery = true)
    List<Object[]> findBooksWithAllRelations();

    @Query(value = "SELECT DISTINCT b.id_book, b.title, b.description, " +
            "p.name AS publisherName, a.first_name AS authorName, c.name AS categoryName " +
            "FROM book b " +
            "LEFT JOIN book_publisher bp ON b.id_book = bp.id_book " +
            "LEFT JOIN publisher p ON bp.id_publisher = p.id_publisher " +
            "LEFT JOIN book_author ba ON b.id_book = ba.id_book " +
            "LEFT JOIN author a ON ba.id_author = a.id_author " +
            "LEFT JOIN book_category bc ON b.id_book = bc.id_book " +
            "LEFT JOIN category c ON bc.id_category = c.id_category " + // <-- espacio agregado aquí
            "WHERE c.id_category = :id", nativeQuery = true)
    List<Object[]> filterBook(@Param("id") int id);

}
