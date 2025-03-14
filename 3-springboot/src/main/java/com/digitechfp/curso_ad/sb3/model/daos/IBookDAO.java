package com.digitechfp.curso_ad.sb3.model.daos;

import com.digitechfp.curso_ad.sb3.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBookDAO extends JpaRepository<Book, Integer> {
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    // No additional methods are needed for basic CRUD operations
    // The JpaRepository interface provides methods like save, findById, findAll, deleteById, etc.
    // You can define custom query methods here if needed
    // For example, to find books by title:
    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.author is null")
    List<Book> dataMigrate();
}
