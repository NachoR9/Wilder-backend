package dev.nacho.wilder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nacho.wilder.dtos.GenreDto;
import dev.nacho.wilder.services.GenreService;

@RestController
@RequestMapping(path = "${api-endpoint}/genres")
public class GenreController {

    private GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping
    public List<GenreDto> index() {
        return service.getAll();
    }

}
