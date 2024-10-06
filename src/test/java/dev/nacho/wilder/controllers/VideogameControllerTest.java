package dev.nacho.wilder.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.dtos.CreateVideogameDto;
import dev.nacho.wilder.dtos.UpdateVideogameDto;
import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.services.VideogameService;

@ExtendWith(MockitoExtension.class)
public class VideogameControllerTest {
    
    @Mock
    private VideogameService service;

    @InjectMocks
    private VideogameController controller;

    @Test
    void testCreate() {
        CreateVideogameDto newGame = new CreateVideogameDto();
        assertDoesNotThrow(() -> controller.create(newGame));
    }

    @Test
    void testDelete() {
        assertDoesNotThrow(() -> controller.delete(87L));
    }

    @Test
    void testGet() {
        VideogameDto dto = new VideogameDto();
        when(service.get(87L)).thenReturn(dto);

        VideogameDto game = controller.get(87L);

        assertEquals(dto, game);
    }

    @Test
    void testIndex() {
        List<VideogameDto> dtos = List.of(new VideogameDto(), new VideogameDto());
        when(service.getAll(5L)).thenReturn(dtos);

        List<VideogameDto> games = controller.index(5L);

        assertEquals(dtos, games);
    }

    @Test
    void testUpdate() {
        UpdateVideogameDto dto = new UpdateVideogameDto();
        
        assertDoesNotThrow(() -> controller.update(dto, 87L));
    }
}
