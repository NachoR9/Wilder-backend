package dev.nacho.wilder.services;

import org.springframework.stereotype.Service;

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

}
