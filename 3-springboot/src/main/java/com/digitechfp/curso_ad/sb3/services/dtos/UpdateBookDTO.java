package com.digitechfp.curso_ad.sb3.services.dtos;

import com.digitechfp.curso_ad.sb3.model.entities.Author;
import lombok.Data;

@Data
public class UpdateBookDTO {
    private String title;
    private String genre;
    private Integer authorId;
}