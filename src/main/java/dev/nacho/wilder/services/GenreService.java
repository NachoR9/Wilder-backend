package dev.nacho.wilder.services;

import java.util.List;
import org.springframework.stereotype.Service;

import dev.nacho.wilder.dtos.GenreDto;
import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.repositories.GenreRepository;

@Service
public class GenreService {
    private GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<GenreDto> getAll() {
        List<Genre> genres = repository.findAll();
        return genres.stream()
        .map(v ->{
            GenreDto dto = new GenreDto();
            dto.setId(v.getId());
            dto.setName(v.getName());
            return dto;
        }).toList();
    }
}
