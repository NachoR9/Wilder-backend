package dev.nacho.wilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.nacho.wilder.models.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Long>{
    
}
