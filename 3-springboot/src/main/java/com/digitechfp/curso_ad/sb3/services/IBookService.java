package com.digitechfp.curso_ad.sb3.services;

import com.digitechfp.curso_ad.sb3.model.entities.Author;
import com.digitechfp.curso_ad.sb3.model.entities.Book;
import com.digitechfp.curso_ad.sb3.services.dtos.CreateBookDTO;
import com.digitechfp.curso_ad.sb3.services.dtos.UpdateBookDTO;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Integer id);
    Optional<Author> getAuthorByBookId(Integer id);
    Optional<Book> createBook(CreateBookDTO bookDTO);
    Optional<Book> updateBook(Integer id, UpdateBookDTO bookDTO);
    boolean deleteBook(Integer id);
}