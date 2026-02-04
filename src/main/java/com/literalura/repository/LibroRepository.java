package com.literalura.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.literalura.model.Book;

@Repository
public interface LibroRepository extends CrudRepository<Book, Long> {
    List<Book> findByAuthors_Name(String name);

    @Query("""
                SELECT b FROM Book b
                LEFT JOIN FETCH b.authors
                LEFT JOIN FETCH b.languages
                WHERE b.idBook = :idBook
            """)
    Optional<Book> findByIdBookWithAuthorsAndLanguages(@Param("idBook") Long idBook);

    List<Book> findDistinctByLanguagesIn(Collection<String> languages);

}
