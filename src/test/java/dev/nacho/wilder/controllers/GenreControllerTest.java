package dev.nacho.wilder.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.dtos.GenreDto;
import dev.nacho.wilder.services.GenreService;

@ExtendWith(MockitoExtension.class)
public class GenreControllerTest {

    @Mock
    private GenreService service;

    @InjectMocks
    private GenreController controller;

    @Test
    void testIndex() {
        List<GenreDto> genres = List.of(new GenreDto(), new GenreDto(), new GenreDto());
        when(service.getAll()).thenReturn(genres);

        List<GenreDto> result = controller.index();
        
        assertEquals(genres, result);
    }
}
