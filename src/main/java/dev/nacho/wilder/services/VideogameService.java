package dev.nacho.wilder.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.models.Videogame;
import dev.nacho.wilder.repositories.VideogameRepository;

@Service
public class VideogameService {

    private VideogameRepository repository;

    public VideogameService(VideogameRepository repository) {
        this.repository = repository;
    }

    public List<VideogameDto> getAll() {
        List<Videogame> videogames = repository.findAll();
        return videogames.stream()
            .map(v -> {
                VideogameDto dto = new VideogameDto();
                dto.setId(v.getId());
                dto.setName(v.getName());
                dto.setGenres(v.getGenres().stream().map(g -> g.getName()).toList());
                dto.setReleaseDate(v.getReleaseDate());
                dto.setCompany(v.getCompany());
                dto.setPlatform(v.getPlatform());
                return dto;
            }).toList();
    }
    
}
