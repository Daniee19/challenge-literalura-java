package com.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @JsonProperty("birth_year")
        @Column(name = "birth_year")
        private Integer birthYear;

        @JsonProperty("death_year")
        @Column(name = "death_year")
        private Integer deathYear;

        public Author() {
        }

        public Author(String name, Integer birth_year, Integer death_year) {
                this.name = name;
                this.birthYear = birth_year;
                this.deathYear = death_year;
        }

        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Integer getBirthYear() {
                return this.birthYear;
        }

        public void setBirthYear(Integer birthYear) {
                this.birthYear = birthYear;
        }

        public Integer getDeathYear() {
                return this.deathYear;
        }

        public void setDeathYear(Integer deathYear) {
                this.deathYear = deathYear;
        }

}
