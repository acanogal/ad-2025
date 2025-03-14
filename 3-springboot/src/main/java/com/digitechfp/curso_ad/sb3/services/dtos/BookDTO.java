package com.digitechfp.curso_ad.sb3.services.dtos;

import com.digitechfp.curso_ad.sb3.model.entities.Book;
import lombok.Data;

@Data
public class BookDTO {
    private Integer id;
    private String title;
    private String genre;
    private AuthorDTO author;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = new AuthorDTO(book.getAuthor());
        this.genre = book.getGenre();
    }

}
