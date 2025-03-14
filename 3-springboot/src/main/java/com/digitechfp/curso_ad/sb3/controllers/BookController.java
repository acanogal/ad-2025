package com.digitechfp.curso_ad.sb3.controllers;

import com.digitechfp.curso_ad.sb3.model.daos.IAuthorDAO;
import com.digitechfp.curso_ad.sb3.model.daos.IBookDAO;
import com.digitechfp.curso_ad.sb3.model.entities.Author;
import com.digitechfp.curso_ad.sb3.model.entities.Book;
import com.digitechfp.curso_ad.sb3.services.IBookService;
import com.digitechfp.curso_ad.sb3.services.dtos.AuthorDTO;
import com.digitechfp.curso_ad.sb3.services.dtos.BookDTO;
import com.digitechfp.curso_ad.sb3.services.dtos.CreateBookDTO;
import com.digitechfp.curso_ad.sb3.services.dtos.UpdateBookDTO;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        List<Book> books = (List<Book>) this.bookService.getAllBooks();
        return books.stream().map(BookDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        Optional<Book> book = this.bookService.getBookById(id);
        return book.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new BookDTO(book.get()));
    }

    @GetMapping("/{id}/author")
    public ResponseEntity<AuthorDTO> getAuthorByBookId(@PathVariable Integer id) {
        Optional<Author> author = this.bookService.getAuthorByBookId(id);
        return author.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new AuthorDTO(author.get()));
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody CreateBookDTO book) {
        Optional<Book> newBook = bookService.createBook(book);
        return newBook.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok(new BookDTO(newBook.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer id, @RequestBody UpdateBookDTO bookDetails) {
        Optional<Book> book = bookService.updateBook(id, bookDetails);
        return book.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(new BookDTO(book.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Boolean exists = bookService.deleteBook(id);
        if (exists) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}