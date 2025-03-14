package com.digitechfp.curso_ad.sb3.services.dtos;

import com.digitechfp.curso_ad.sb3.model.entities.Author;
import lombok.Data;

@Data
public class AuthorDTO {
    private Integer id;
    private String name;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}
