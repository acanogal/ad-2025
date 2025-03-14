package com.digitechfp.curso_ad.sb2.controllers;

import com.digitechfp.curso_ad.sb2.daos.IBookDAO;
import com.digitechfp.curso_ad.sb2.model.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private IBookDAO bookDAO;
    // This class will handle HTTP requests related to books
    // You can define methods here to handle different endpoints
    // For example, to get all books, add a method like this:
    @GetMapping
    public List<Book> getAllBooks() {
        return (List<Book>) this.bookDAO.findAll();
    }

    @GetMapping ("/{id}")
    public Book getBookById(@PathVariable  Long id) {
        return this.bookDAO.findById(id).orElse(null);
    }

}
