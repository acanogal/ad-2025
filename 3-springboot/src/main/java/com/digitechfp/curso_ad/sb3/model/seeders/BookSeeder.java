package com.digitechfp.curso_ad.sb3.model.seeders;

import com.digitechfp.curso_ad.sb3.model.daos.IAuthorDAO;
import com.digitechfp.curso_ad.sb3.model.daos.IBookDAO;
import com.digitechfp.curso_ad.sb3.model.entities.Author;
import com.digitechfp.curso_ad.sb3.model.entities.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookSeeder implements CommandLineRunner {

    private final IBookDAO bookDAO;
    private final IAuthorDAO authorDAO;

    public BookSeeder(IBookDAO bookDAO, IAuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        seedBooks();
    }

    private void seedBooks() {
        Author acg = new Author();
        acg.setName("Antonio Cano Galindo");
        acg = saveOrUpdateAuthor(acg);
        Author ggm = new Author();
        ggm.setName("Gabriel García Márquez");
        ggm = saveOrUpdateAuthor(ggm);
        Book book1 = new Book();
        book1.setTitle("Acceso a datos");
        book1.setAuthor(acg);
        book1.setGenre("Tortura");

        Book book2 = new Book();
        book2.setTitle("Cien años de soledad");
        book2.setAuthor(ggm);
        book2.setGenre("Novela");

        saveOrUpdateBook(book1);
        saveOrUpdateBook(book2);
    }

    private void saveOrUpdateBook(Book book) {
        Optional<Book> existingBook = bookDAO.findByTitle(book.getTitle());
        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setGenre(book.getGenre());
            bookDAO.save(bookToUpdate);
        } else {
            bookDAO.save(book);
        }
    }

    private Author saveOrUpdateAuthor(Author author) {
        Optional<Author> existing = authorDAO.findByName(author.getName());
        return existing.isEmpty() ? authorDAO.save(author) : existing.get();
    }

}