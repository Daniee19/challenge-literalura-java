package com.literalura.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long idBook;

        private Long id;
        String title;

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "book_authors", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
        private Set<Author> authors = new HashSet<>();

        @ElementCollection
        @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
        @Column(name = "language")
        private Set<String> languages = new HashSet<>();

        @JsonAlias("download_count")
        private Integer downloadCount;

        // GETTERS AND SETTERS

        public Set<Author> getAuthors() {
                return this.authors;
        }

        public void setAuthors(Set<Author> authors) {
                this.authors = authors;
        }

        // constructor vacío
        public Book() {
        }

        public void setIdBook(Long idBook) {
                this.idBook = idBook;
        }

        public Long getId() {
                return this.id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        // getters y setters
        public Long getIdBook() {
                return idBook;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public Set<String> getLanguages() {
                return this.languages;
        }

        public void setLanguages(Set<String> languages) {
                this.languages = languages;
        }

        public Integer getDownloadCount() {
                return downloadCount;
        }

        public void setDownloadCount(Integer downloadCount) {
                this.downloadCount = downloadCount;
        }

        @Override
        public String toString() {
                // TODO Auto-generated method stub
                return super.toString();
        }
}
