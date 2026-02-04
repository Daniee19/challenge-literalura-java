# 📚 Literalura

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-red)
![Status](https://img.shields.io/badge/Status-Finalizado-success)

---

## 🚀 Descripción

**Literalura** es una aplicación de consola desarrollada en **Java con Spring Boot** que consume la API pública **Gutendex** para buscar libros y autores, almacenarlos en una base de datos **PostgreSQL** y permitir consultas avanzadas usando **Spring Data JPA**.

El proyecto se enfoca en buenas prácticas de backend, persistencia de datos y consumo de APIs REST.

---

## ✨ Funcionalidades

✔ Buscar libros por título desde la API Gutendex  
✔ Guardar libros, autores e idiomas en PostgreSQL  
✔ Listar libros registrados  
✔ Listar autores registrados  
✔ Listar autores vivos en un año específico  
✔ Listar libros por idioma  
✔ Buscar libros por autor  

---

## 🧠 Tecnologías Utilizadas

- ☕ Java 17  
- 🌱 Spring Boot  
- 🧩 Spring Data JPA  
- 🐘 PostgreSQL  
- 🔄 Hibernate  
- 🌐 Gutendex API  
- 📦 Maven  

---

## 🗄️ Modelo de Datos

### 📘 Book
- ID autogenerado (PK)
- ID externo (API Gutendex)
- Título
- Número de descargas
- Idiomas
- Relación con autores

### ✍️ Author
- ID autogenerado
- Nombre
- Año de nacimiento
- Año de fallecimiento (nullable)
- Relación con libros

### 🌍 Idiomas
- Relación `@ElementCollection`
- Tabla independiente `book_languages`

---

## 🔗 Relaciones JPA

- Book ↔ Author  
  - `@ManyToMany
- Book ↔ Languages  
  - `@ElementCollection`
- Uso de `CascadeType.ALL`
- Manejo de Lazy Loading
- Uso de `Set` para evitar `MultipleBagFetchException`

---

## 🛠️ Configuración PostgreSQL

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## 📡 API Utilizada

**Gutendex API**  
https://gutendex.com/books/

Ejemplo:
```
https://gutendex.com/books/?search=pride
```

---

## 🧪 Menú de la Aplicación

```
1) Buscar libro por título
2) Listar libros registrados
3) Listar autores registrados
4) Listar autores vivos en un año
5) Listar libros por idioma
0) Salir
```

---

## ⚠️ Problemas Técnicos Resueltos

✔ LazyInitializationException  
✔ MultipleBagFetchException  
✔ Snake_case vs camelCase  
✔ Relaciones bidireccionales  
✔ IDs externos vs IDs internos  
✔ Persistencia automática con Cascade  
✔ Queries derivadas en Spring Data  

---

## 📌 Aprendizajes Clave

- Diferencia entre ID de negocio y ID de base de datos
- Cuándo usar List vs Set en JPA
- Manejo correcto de sesiones Hibernate
- Buen diseño de entidades
- Consultas avanzadas con Spring Data

---

## 👤 Autor

**Daniel Castañeda**  
📍 Perú  
📚 Proyecto académico de Backend con Java y Spring

---

## ⭐ Estado del Proyecto

🟢 Finalizado y funcional