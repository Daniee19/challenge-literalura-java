package com.literalura;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.literalura.client.LibroClient;
import com.literalura.model.ApiResponse;
import com.literalura.model.Author;
import com.literalura.model.Book;
import com.literalura.service.AuthorService;
import com.literalura.service.ConvierteDatos;
import com.literalura.service.LibroService;

import jakarta.transaction.Transactional;

import com.literalura.principal.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	LibroService libroService;

	@Autowired
	AuthorService authorService;

	@Autowired
	Principal p;

	Scanner scan = new Scanner(System.in);
	ConvierteDatos convierteDatos = new ConvierteDatos();

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	// Para que no sea lazy y pueda cargar otras llamadas desde el book (tabla
	// padre)
	public void run(String... args) throws Exception {
		String opcion;

		do {
			opcion = p.mensajeInicio();

			// * Numero 1
			if (opcion.equals("1")) {
				System.out.println("Ingrese el titulo del libro que desea buscar");
				String titulo = scan.nextLine();

				ApiResponse api = libroService.obtenerPorTitulo(titulo);
				Book results = api.results().get(0);

				// Subir Libro a la base de datos
				libroService.addBook(results);

				p.mensaje1Libro(results);
			}

			if (opcion.equals("2")) {
				// * Lo que voy a hacer es listar todos los libros de la base de datos
				if (!libroService.getAllBooks().iterator().hasNext()) {
					System.out.println("\nAún no hay libros registrados\n");
					return;
				}

				for (Book t : libroService.getAllBooks()) {
					p.mensaje1Libro(t);
				}
			}

			if (opcion.equals("3")) {

				for (Author a : authorService.getAllAuthors()) {
					p.mensajeRespuestaAutores(a);
				}
			}

			if (opcion.equals("4")) {
				System.out.println("Ingrese el año vivo del autor que desea buscar");
				Integer anio = Integer.parseInt(scan.nextLine());
				System.out.println("\nEl anio es: " + anio);
				List<Author> autores = authorService.findAuthorsAliveInYear(anio);

				if (autores.isEmpty()) {
					System.out.println("\nNo se encontraron autores vivos en ese año\n");
				} else {
					System.out.println("\nAutores vivos en " + anio + ":\n");

					for (Author a : autores) {
						p.mensajeRespuestaAutores(a);
					}
				}
			}

			if (opcion.equals("5")) {
				System.out.println("Ingrese el idioma para buscar los libros");
				System.out.println("""
						es - español
						en - inglés
						fr - francés
						pt - portugués
						""");
				String idioma = scan.nextLine();
				List<Book> librosPorIdioma = libroService.findBooksByLanguage(idioma);
				librosPorIdioma.forEach(libro -> p.mensaje1Libro(libro));
			}

		} while (!opcion.equals("0"));
	}

}
