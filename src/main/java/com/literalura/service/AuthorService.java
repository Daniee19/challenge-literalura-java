package com.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.model.Author;
import com.literalura.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Iterable<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> findAuthorsAliveInYear(Integer year) {

        List<Author> muertosDespues = authorRepository
                .findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);

        List<Author> aunVivos = authorRepository
                .findByBirthYearLessThanEqualAndDeathYearIsNull(year);

        muertosDespues.addAll(aunVivos);
        return muertosDespues;
    }

}
