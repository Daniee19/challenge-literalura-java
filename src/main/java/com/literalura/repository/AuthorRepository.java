package com.literalura.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.literalura.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(
            Integer birth_year,
            Integer death_year);

    // Para autores aún vivos
    List<Author> findByBirthYearLessThanEqualAndDeathYearIsNull(Integer birth_year);
}
