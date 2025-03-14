package com.digitechfp.curso_ad.sb3.model.daos;

import com.digitechfp.curso_ad.sb3.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthorDAO extends JpaRepository<Author, Integer> {
    Optional<Author> findByName(String name);
}
