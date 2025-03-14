package com.digitechfp.curso_ad.sb3.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Optional;

@Entity
@Table(name = "books", indexes = {
        @Index(name = "books_title_idx", columnList = "title", unique = true),
})
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_gen")
    @SequenceGenerator(name = "books_id_gen", sequenceName = "books_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @Column(name="genre", nullable = true)
    private String genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties
    private Author author;


}
