package dev.nacho.wilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.models.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Long>{
    
    List<Videogame> findByGenres(Genre genre);
}
