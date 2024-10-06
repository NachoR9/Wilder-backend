package dev.nacho.wilder.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.exceptions.UserNotFoundException;
import dev.nacho.wilder.exceptions.VideogameNotFoundException;
import dev.nacho.wilder.models.User;
import dev.nacho.wilder.models.Videogame;
import dev.nacho.wilder.repositories.UserRepository;
import dev.nacho.wilder.repositories.VideogameRepository;

@Service
public class UserVideogameService {
    
    private UserRepository userRepository;
    private VideogameRepository videogameRepository;
    

    public UserVideogameService(UserRepository userRepository, VideogameRepository videogameRepository) {
        this.userRepository = userRepository;
        this.videogameRepository = videogameRepository;
    }

    public void addVideogame(Long videogameId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Videogame selectedGame = videogameRepository.findById(videogameId).orElseThrow(VideogameNotFoundException::new);
        boolean hasVideogame = user.getVideogames().stream().anyMatch(v -> v.getId() == videogameId);
        if (!hasVideogame) {
            user.getVideogames().add(selectedGame);
            userRepository.save(user);
        }
    }

    public List<VideogameDto> getAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Set<Videogame> videogames = user.getVideogames();
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

    public void delete(Long videogameId, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Set<Videogame> filteredVideogames = new HashSet<>();
        for (Videogame game : user.getVideogames()) {
            if (game.getId() != videogameId) {
                filteredVideogames.add(game);
            }
        }

        user.setVideogames(filteredVideogames);
        userRepository.save(user);
    }

}
