package dev.nacho.wilder.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "videogames")
public class Videogame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany
    private List<Genre> genres;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    @Column(name = "company")
    private String company;
    @Column(name = "platform")
    private String platform;
}