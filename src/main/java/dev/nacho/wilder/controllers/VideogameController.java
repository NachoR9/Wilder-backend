package dev.nacho.wilder.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nacho.wilder.dtos.VideogameDto;
import dev.nacho.wilder.services.VideogameService;

@RestController
@RequestMapping(path = "${api-endpoint}/videogames")
public class VideogameController {

    private VideogameService service;

    public VideogameController(VideogameService service) {
        this.service =service;
    }

    @GetMapping
    public List<VideogameDto> index() {
        return service.getAll();
    }
    
}
