package dev.nacho.wilder.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nacho.wilder.dtos.AddUserVideogameDto;
import dev.nacho.wilder.models.SecurityUser;
import dev.nacho.wilder.services.UserVideogameService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "${api-endpoint}/user/videogames")
public class UserVideogameController {

        private UserVideogameService service;

        public UserVideogameController(UserVideogameService service) {
            this.service = service;
        }

        @PostMapping
        public void add(@RequestBody @Valid AddUserVideogameDto addVideogameDto) {
            SecurityUser user = (SecurityUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
            service.addVideogame(addVideogameDto.getId(), user.getId());
        }
}
