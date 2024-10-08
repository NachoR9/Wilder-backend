package dev.nacho.wilder.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.nacho.wilder.dtos.CreateVideogameDto;
import dev.nacho.wilder.dtos.UpdateVideogameDto;
import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.exceptions.VideogameNotFoundException;
import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.models.Videogame;
import dev.nacho.wilder.repositories.VideogameRepository;

@Service
public class VideogameService {

    private VideogameRepository repository;

    public VideogameService(VideogameRepository repository) {
        this.repository = repository;
    }

    public List<VideogameDto> getAll(Long genreId) {
        List<Videogame> videogames;
        if (genreId != null) {
            Genre genre = new Genre();
            genre.setId(genreId);
            videogames = repository.findByGenres(genre);
        }  else {
            videogames = repository.findAll();
        }
        return videogames.stream()
            .map(v -> {
                VideogameDto dto = new VideogameDto();
                dto.setId(v.getId());
                dto.setName(v.getName());
                dto.setGenres(v.getGenres().stream().map(g -> g.getName()).toList());
                dto.setReleaseDate(v.getReleaseDate());
                dto.setCompany(v.getCompany());
                dto.setPlatform(v.getPlatform());
                dto.setImage(v.getImage());
                return dto;
            }).toList();
    }

    public VideogameDto get(Long id) {
        Videogame videogame = repository.findById(id).orElseThrow(VideogameNotFoundException::new);
        VideogameDto dto = new VideogameDto();
        dto.setId(videogame.getId());
        dto.setName(videogame.getName());
        dto.setGenres(videogame.getGenres().stream().map(g -> g.getName()).toList());
        dto.setReleaseDate(videogame.getReleaseDate());
        dto.setCompany(videogame.getCompany());
        dto.setPlatform(videogame.getPlatform());
        dto.setImage(videogame.getImage());
        return dto;
    }

    public void create(CreateVideogameDto newVideogame) {
        Videogame videogame = new Videogame();
        videogame.setName(newVideogame.getName());
        videogame.setGenres(newVideogame.getGenres().stream().map(g -> {
            Genre genre = new Genre();
            genre.setId(g);
            return genre;
        }).toList());
        videogame.setReleaseDate(newVideogame.getReleaseDate());
        videogame.setCompany(newVideogame.getCompany());
        videogame.setPlatform(newVideogame.getPlatform());
        repository.save(videogame);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void update(Long id, UpdateVideogameDto updateVideogameDto) {
        Videogame videogame = repository.findById(id).orElseThrow(VideogameNotFoundException::new);

        videogame.setName(updateVideogameDto.getName());
        videogame.setReleaseDate(updateVideogameDto.getReleaseDate());
        videogame.setCompany(updateVideogameDto.getCompany());
        videogame.setPlatform(updateVideogameDto.getPlatform());
        videogame.setImage(updateVideogameDto.getImage());
        List<Genre> genres = new ArrayList<>();
        for (Long genreId : updateVideogameDto.getGenres()) {
            Genre genre = new Genre();
            genre.setId(genreId);
            genres.add(genre);
        }
        videogame.setGenres(genres);

        repository.save(videogame);
    }
    
}
