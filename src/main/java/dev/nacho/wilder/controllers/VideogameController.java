package dev.nacho.wilder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.nacho.wilder.dtos.CreateVideogameDto;
import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.services.VideogameService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/videogames")
public class VideogameController {

    private VideogameService service;

    public VideogameController(VideogameService service) {
        this.service =service;
    }

    @GetMapping
    public List<VideogameDto> index(@RequestParam(name = "genre", required = false) Long genre) {
        return service.getAll(genre);
    }

    @PostMapping
    public void create(@RequestBody @Valid CreateVideogameDto newVideogameDto) {
        service.create(newVideogameDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id", required = true) Long id) {
        service.delete(id);
    }
    
}
