package com.digitechfp.curso_ad.sb3.model.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "authors", indexes = {
        @Index(name = "author_name_idx", columnList = "name", unique = true),
})
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_gen")
    @SequenceGenerator(name = "authors_id_gen", sequenceName = "authors_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties
    private Set<Book> books = new LinkedHashSet<>();

}
