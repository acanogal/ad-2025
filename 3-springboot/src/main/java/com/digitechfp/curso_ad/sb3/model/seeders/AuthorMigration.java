package com.digitechfp.curso_ad.sb3.model.seeders;

import com.digitechfp.curso_ad.sb3.model.daos.IAuthorDAO;
import com.digitechfp.curso_ad.sb3.model.daos.IBookDAO;
import com.digitechfp.curso_ad.sb3.model.entities.Author;
import com.digitechfp.curso_ad.sb3.model.entities.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorMigration implements CommandLineRunner {

    private final IBookDAO bookDAO;
    private final IAuthorDAO authorDAO;

    public AuthorMigration(IBookDAO bookDAO, IAuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        execute();
    }

    private void execute() {
        for (Book book : bookDAO.dataMigrate()) {
            migrateBook(book);
        }
    }
    private void migrateBook(Book book) {
        Optional<Author> author = authorDAO.findByName(book.getAuthor().getName());
        if (author.isEmpty()) {
            book.setAuthor(createAuthor(book));
        } else {
            book.setAuthor(author.get());
        }
        bookDAO.save(book);
    }

    private Author createAuthor(Book book) {
        Author author = new Author();
        author.setName(book.getAuthor().getName());
        return authorDAO.save(author);
    }

}

