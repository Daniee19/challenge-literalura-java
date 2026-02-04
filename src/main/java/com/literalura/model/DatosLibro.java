package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
                @JsonAlias("Title") String titulo,
                @JsonAlias("Author") String autor,
                @JsonAlias("language") String idioma,
                @JsonAlias("number_downloads") String nro_descargas) {
}
//TODO buscar los nombres exclusivos de la opcion 1 del json y convertirlo a objeto, luego ver a que se refiere con la opcion 2 y 3 con valores registrados, acaso de la base de datos? No entendí la vrd