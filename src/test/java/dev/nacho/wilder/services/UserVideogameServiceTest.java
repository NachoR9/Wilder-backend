package dev.nacho.wilder.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.exceptions.UserNotFoundException;
import dev.nacho.wilder.exceptions.VideogameNotFoundException;
import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.models.User;
import dev.nacho.wilder.models.Videogame;
import dev.nacho.wilder.repositories.UserRepository;
import dev.nacho.wilder.repositories.VideogameRepository;

@ExtendWith(MockitoExtension.class)
public class UserVideogameServiceTest {
    
    @Mock
    private UserRepository userRepository;
    @Mock
    private VideogameRepository videogameRepository;

    @InjectMocks
    private UserVideogameService service;

    @Test
    void testAddVideogame() {
        User user = new User();
        user.setVideogames(new HashSet<>());
        when(userRepository.findById(7L)).thenReturn(Optional.of(user));
        when(videogameRepository.findById(87L)).thenReturn(Optional.of(new Videogame()));

        assertDoesNotThrow(() -> service.addVideogame(87L, 7L));
    }

    @Test
    void testAddVideogame_userNotFound() {
        when(userRepository.findById(7L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.addVideogame(87L, 7L));
    }

    @Test
    void testAddVideogame_gameNotFound() {
        User user = new User();
        user.setVideogames(new HashSet<>());
        when(userRepository.findById(7L)).thenReturn(Optional.of(user));
        when(videogameRepository.findById(87L)).thenReturn(Optional.empty());

        assertThrows(VideogameNotFoundException.class, () -> service.addVideogame(87L, 7L));
    }

    @Test
    void testDelete() {
        User user = new User();
        Set<Videogame> games = new HashSet<>();
        Videogame g1 = new Videogame();
        g1.setId(24L);
        Videogame g2 = new Videogame();
        g2.setId(87L);
        games.add(g1);
        games.add(g2);
        user.setVideogames(games);
        when(userRepository.findById(7L)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> service.delete(87L, 7L));
    }

    @Test
    void testGetAll() {
        Videogame game = new Videogame();
        game.setId(87L);
        game.setName("game");
        Genre g = new Genre();
        g.setName("RPG");
        g.setId(3L);
        game.setGenres(List.of(g));
        game.setReleaseDate(LocalDate.now().minusYears(2l));
        game.setCompany("test-company");
        game.setPlatform("test-platform");
        game.setImage("test-image");
        User user = new User();
        user.setId(7L);
        user.setVideogames(Set.of(game));

        when(userRepository.findById(7L)).thenReturn(Optional.of(user));

        List<VideogameDto> games = service.getAll(7L);

        assertEquals(1, games.size());
        assertEquals(87L, games.get(0).getId());
        assertEquals("game", games.get(0).getName());
        assertEquals("RPG", games.get(0).getGenres().get(0));
        assertEquals(LocalDate.now().minusYears(2l), games.get(0).getReleaseDate());
        assertEquals("test-company", games.get(0).getCompany());
        assertEquals("test-platform", games.get(0).getPlatform());
        assertEquals("test-image", games.get(0).getImage());
    }
}
