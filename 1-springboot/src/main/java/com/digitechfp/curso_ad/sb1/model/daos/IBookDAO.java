package com.digitechfp.curso_ad.sb1.model.daos;

import com.digitechfp.curso_ad.sb1.model.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IBookDAO extends CrudRepository<Book, Long> {
    // No additional methods are needed for basic CRUD operations
    // The CrudRepository interface provides methods like save, findById, findAll, deleteById, etc.
    // You can define custom query methods here if needed
    // For example, to find books by title:
    List<Book> findByTitle(String title);
    // Or to find books by author:

}
