package dev.nacho.wilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.nacho.wilder.models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    
}
