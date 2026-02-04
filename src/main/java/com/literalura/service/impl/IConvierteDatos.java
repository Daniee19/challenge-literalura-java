package com.literalura.service.impl;

import org.springframework.stereotype.Component;

@Component
public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
