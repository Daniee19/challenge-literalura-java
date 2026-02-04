package com.literalura.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.literalura.client.LibroClient;
import com.literalura.model.ApiResponse;
import com.literalura.model.Book;
import com.literalura.repository.LibroRepository;

import jakarta.transaction.Transactional;

@Service
public class LibroService {

    private final LibroClient client;

    public LibroService(LibroClient client) {
        this.client = client;
    }

    @Autowired
    LibroRepository libroRepository;

    // Lógica 1
    public ApiResponse obtenerPorTitulo(String titulo) throws IOException, InterruptedException {
        return client.obtenerPorTitulo(titulo);
    }

    public Iterable<Book> getAllBooks() {
        return libroRepository.findAll();
    }

    @Transactional
    public Book addBook(Book book) {
        return libroRepository.save(book);
    }

    public List<Book> findBooksByAuthorName(String nameAuthor) {
        return libroRepository.findByAuthors_Name(nameAuthor);
    }

    @Transactional
    public Book getBookWithLanguages(Long id) {
        Book book = libroRepository.findById(id).orElseThrow();
        book.getLanguages().size(); // fuerza carga
        return book;
    }

    @Transactional
    public Book getBookWithAllRelations(Long idBook) {
        return libroRepository.findByIdBookWithAuthorsAndLanguages(idBook)
                .orElseThrow();
    }

    public List<Book> findBooksByLanguage(String idiomas) {
        List<String> listaIdiomas = Arrays.stream(idiomas.split(","))
                .map(String::trim)
                .toList();

        return libroRepository.findDistinctByLanguagesIn(listaIdiomas);
    }
}
