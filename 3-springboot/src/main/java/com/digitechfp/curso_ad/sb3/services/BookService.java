package com.digitechfp.curso_ad.sb3.services;

import com.digitechfp.curso_ad.sb3.model.daos.IAuthorDAO;
import com.digitechfp.curso_ad.sb3.model.daos.IBookDAO;
import com.digitechfp.curso_ad.sb3.model.entities.Author;
import com.digitechfp.curso_ad.sb3.model.entities.Book;
import com.digitechfp.curso_ad.sb3.services.dtos.CreateBookDTO;
import com.digitechfp.curso_ad.sb3.services.dtos.UpdateBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    private final IBookDAO bookDAO;
    private final IAuthorDAO authorDAO;

    @Autowired
    public BookService(IBookDAO bookDAO, IAuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookDAO.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookDAO.findById(id);
    }

    public Optional<Author> getAuthorByBookId(Integer id) {
        Optional<Book> book = bookDAO.findById(id);
        return Optional.of(book.get().getAuthor());
    }

    public Optional<Book> createBook(CreateBookDTO bookDTO) {
        Optional<Author> author = authorDAO.findByName(bookDTO.getAuthorName());
        if (author.isEmpty()) {
            return Optional.empty();
        }
        Book newBook = new Book();
        newBook.setTitle(bookDTO.getTitle());
        newBook.setGenre(bookDTO.getGenre());
        newBook.setAuthor(author.get());
        return Optional.of(bookDAO.save(newBook));
    }

    public Optional<Book> updateBook(Integer id, UpdateBookDTO bookDTO) {
        Optional<Book> book = bookDAO.findById(id);
        Optional<Author> author = authorDAO.findById(bookDTO.getAuthorId());
        if (book.isPresent() && author.isPresent()) {
            Book bookToUpdate = book.get();
            bookToUpdate.setTitle(bookDTO.getTitle());
            bookToUpdate.setAuthor(author.get());
            bookToUpdate.setGenre(bookDTO.getGenre());
            return Optional.of(bookDAO.save(bookToUpdate));
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteBook(Integer id) {
        Optional<Book> book = bookDAO.findById(id);
        if (book.isPresent()) {
            bookDAO.delete(book.get());
            return true;
        } else {
            return false;
        }
    }
}
