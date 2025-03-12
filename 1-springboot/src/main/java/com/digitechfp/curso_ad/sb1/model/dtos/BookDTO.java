package com.digitechfp.curso_ad.sb1.model.dtos;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookDTO {
    private Integer id;
    private String title;
    private String genre;
    private String description;
    private String publisher;
    private Integer pages;
    private String imageUrl;
    private LocalDate publishDate;
    private Integer authorId;
    private String authorName;
}