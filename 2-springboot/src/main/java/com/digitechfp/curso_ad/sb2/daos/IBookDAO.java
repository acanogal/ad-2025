package com.digitechfp.curso_ad.sb2.daos;

import com.digitechfp.curso_ad.sb2.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

public interface IBookDAO extends JpaRepository<Book, Long> {
    // No additional methods are needed for basic CRUD operations
    // The JpaRepository interface provides methods like save, findById, findAll, deleteById, etc.
    // You can define custom query methods here if needed
    // For example, to find books by title:
    List<Book> findByAuthor(String author);
    // Or to find books by title:
    List<Book> findByTitle(String title);

}
