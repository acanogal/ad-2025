package com.digitechfp.curso_ad.sb1.controllers;

import com.digitechfp.curso_ad.sb1.mapper.BookMapper;
import com.digitechfp.curso_ad.sb1.model.daos.IBookDAO;
import com.digitechfp.curso_ad.sb1.model.dtos.BookDTO;
import com.digitechfp.curso_ad.sb1.model.entities.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    final
    IBookDAO bookDAO;

    public BookController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    // This class will handle HTTP requests related to books
    // You can define methods here to handle different endpoints
    // For example, to get all books, add a method like this:

    @GetMapping
    public List<BookDTO> getAllBooks() {
         List<Book> books = (List<Book>) this.bookDAO.findAll();
         return BookMapper.toBookDTOList(books);
    }

    // You can also add methods for creating, updating, and deleting books
    // For example:

    // @PostMapping
    // public Book createBook(@RequestBody Book book) {
    //     return bookService.createBook(book);
    // }

    // @PutMapping("/{id}")
    // public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
    //     return bookService.updateBook(id, book);
    // }

    // @DeleteMapping("/{id}")
    // public void deleteBook(@PathVariable Long id) {
    //     bookService.deleteBook(id);
    // }
}
