package com.literalura.client;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.literalura.model.ApiResponse;
import com.literalura.service.ConvierteDatos;

import org.springframework.stereotype.Component;

@Component
public class LibroClient {
        private final HttpClient httpClient = HttpClient.newHttpClient();

        ConvierteDatos conversor;

        public LibroClient(ConvierteDatos conversor) {
                this.conversor = conversor;
        }

        String urlBase = "https://gutendex.com";

        // *1 -> Retornamos una clase ApiResponse, porque retorna una clase con los
        // ***** objetos del json encontrado
        public ApiResponse obtenerPorTitulo(String titulo) throws IOException, InterruptedException {

                // URL
                String url = urlBase + "/books/?search=" +
                                URLEncoder.encode(titulo, StandardCharsets.UTF_8);

                // Petición a la API
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .GET()
                                .build();

                // Respuesta
                HttpResponse<String> response = httpClient.send(
                                request, HttpResponse.BodyHandlers.ofString());

                return conversor.obtenerDatos(response.body(), ApiResponse.class);

        }

}
