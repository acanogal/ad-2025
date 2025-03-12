package com.digitechfp.curso_ad.sb1.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
    @SequenceGenerator(name = "books_id_gen", sequenceName = "books_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "genre", length = 100)
    private String genre;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "author")
    private String author_delete;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

}