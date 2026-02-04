package com.literalura.principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.literalura.model.Author;
import com.literalura.model.Book;
import com.literalura.service.ConvierteDatos;
import com.literalura.service.LibroService;

@Component
public class Principal {

    @Autowired
    LibroService libroService;

    ConvierteDatos convierteDatos = new ConvierteDatos();

    Scanner scan = new Scanner(System.in);

    public String mensajeInicio() {
        System.out.println(
                """
                        Elija la opción a través de su número:
                        ******************
                        1) buscar libro por título
                        2) listar libros registrados
                        3) listar autores registrados
                        4) listar autores vivos en un determinado año
                        5) listar libros por idioma
                        0) salir

                        Ingrese una opción válida
                        """);
        return scan.nextLine();
    }

    public void mensaje1Libro(Book results) {

        Book libro = libroService.getBookWithAllRelations(results.getIdBook());

        String idiomasTexto = String.join(", ", libro.getLanguages());
        String autor = libro.getAuthors().isEmpty()
                ? "Desconocido"
                : libro.getAuthors().iterator().next().getName();

        System.out.println("""
                \n
                    📘 Libro
                    -------------------
                    Título: %s
                    Autor: %s
                    Idiomas: %s
                    Número de descargas: %d
                    \n""".formatted(libro.getTitle(), autor, idiomasTexto,
                libro.getDownloadCount()));
    }

    public void mensajeRespuestaAutores(Author a) {

        System.out.println("-------- Autor(es) --------");
        System.out.println("Autor: " + a.getName());
        System.out.println("Fecha de nacimiento: " + a.getBirthYear());
        System.out.println("Fallecimiento: " + (a.getDeathYear() == null ? "Aún vivo" : a.getDeathYear()));
        List<Book> libros = libroService.findBooksByAuthorName(a.getName());

        if (libros.isEmpty()) {
            System.out.println("Libros: No registrados");
        } else {
            System.out.println("Libros:");
            libros.forEach(b -> System.out.println(" - " + b.getTitle()));
        }

        System.out.println();
    }
}
