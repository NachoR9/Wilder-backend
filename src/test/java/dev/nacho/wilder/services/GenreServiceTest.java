package dev.nacho.wilder.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.dtos.GenreDto;
import dev.nacho.wilder.models.Genre;
import dev.nacho.wilder.repositories.GenreRepository;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository repository;

    @InjectMocks
    private GenreService service;

    @Test
    void testGetAll() {
        Genre g1 = new Genre();
        g1.setId(1L);
        g1.setName("Action");
        Genre g2 = new Genre();
        g2.setId(2L);
        g2.setName("RPG");
        when(repository.findAll()).thenReturn(List.of(g1, g2));

        List<GenreDto> genres = service.getAll();

        assertEquals(2, genres.size());
        assertEquals(1L, genres.get(0).getId());
        assertEquals("Action", genres.get(0).getName());
        assertEquals(2L, genres.get(1).getId());
        assertEquals("RPG", genres.get(1).getName());
    }

    @Test
    void testGetAll_empty() {
        when(repository.findAll()).thenReturn(List.of());
        List<GenreDto> genres = service.getAll();
        assertTrue(genres.isEmpty());
    }
}
