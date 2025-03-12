package com.digitechfp.curso_ad.sb1.mapper;

import com.digitechfp.curso_ad.sb1.model.dtos.BookDTO;
import com.digitechfp.curso_ad.sb1.model.entities.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setPages(book.getPages());
        bookDTO.setImageUrl(book.getImageUrl());
        bookDTO.setPublishDate(book.getPublishDate());
        bookDTO.setAuthorId(book.getAuthor().getId());
        bookDTO.setAuthorName(book.getAuthor().getName());
        return bookDTO;
    }

    public static List<BookDTO> toBookDTOList(List<Book> books) {
        return books.stream()
                .map(BookMapper::toBookDTO)
                // .map(book -> BookMapper.toBookDTO(book))
                .collect(Collectors.toList());
    }
}