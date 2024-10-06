package dev.nacho.wilder.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.dtos.CreateVideogameDto;
import dev.nacho.wilder.dtos.UpdateVideogameDto;
import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.models.Videogame;
import dev.nacho.wilder.repositories.VideogameRepository;

@ExtendWith(MockitoExtension.class)
public class VideogameServiceTest {

    @Mock
    private VideogameRepository videogameRepository;

    @InjectMocks
    private VideogameService service;

    @Test
    void testCreate() {
        CreateVideogameDto game = new CreateVideogameDto();
        game.setName("test-name");
        game.setGenres(List.of(3l, 6L, 7L));
        game.setReleaseDate(LocalDate.now().minusYears(5));
        game.setCompany("test-company");
        game.setPlatform("test-platform");

        assertDoesNotThrow(() -> service.create(game));
    }

    @Test
    void testDelete() {
        assertDoesNotThrow(() -> service.delete(87L));
    }

    @Test
    void testGet() {
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

        when(videogameRepository.findById(87L)).thenReturn(Optional.of(game));

        VideogameDto result = service.get(87L);

        assertEquals(87L, result.getId());
        assertEquals("game", result.getName());
        assertEquals("RPG", result.getGenres().get(0));
        assertEquals(LocalDate.now().minusYears(2l), result.getReleaseDate());
        assertEquals("test-company", result.getCompany());
        assertEquals("test-platform", result.getPlatform());
        assertEquals("test-image", result.getImage());
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

        when(videogameRepository.findAll()).thenReturn(List.of(game));

        List<VideogameDto> games = service.getAll(null);

        assertEquals(1, games.size());
        assertEquals(87L, games.get(0).getId());
        assertEquals("game", games.get(0).getName());
        assertEquals("RPG", games.get(0).getGenres().get(0));
        assertEquals(LocalDate.now().minusYears(2l), games.get(0).getReleaseDate());
        assertEquals("test-company", games.get(0).getCompany());
        assertEquals("test-platform", games.get(0).getPlatform());
        assertEquals("test-image", games.get(0).getImage());
    }

    @Test
    void testGetAll_role() {
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

        when(videogameRepository.findByGenres(any())).thenReturn(List.of(game));

        List<VideogameDto> games = service.getAll(3L);

        assertEquals(1, games.size());
        assertEquals(87L, games.get(0).getId());
        assertEquals("game", games.get(0).getName());
        assertEquals("RPG", games.get(0).getGenres().get(0));
        assertEquals(LocalDate.now().minusYears(2l), games.get(0).getReleaseDate());
        assertEquals("test-company", games.get(0).getCompany());
        assertEquals("test-platform", games.get(0).getPlatform());
        assertEquals("test-image", games.get(0).getImage());
    }

    @Test
    void testUpdate() {
        UpdateVideogameDto game = new UpdateVideogameDto();
        game.setName("test-name");
        game.setGenres(List.of(3l, 6L, 7L));
        game.setReleaseDate(LocalDate.now().minusYears(5));
        game.setCompany("test-company");
        game.setPlatform("test-platform");

        when(videogameRepository.findById(87L)).thenReturn(Optional.of(new Videogame()));

        assertDoesNotThrow(() -> service.update(87L, game));
    }
}
