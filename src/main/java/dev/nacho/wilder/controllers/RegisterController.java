package dev.nacho.wilder.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.nacho.wilder.dtos.UserDto;
import dev.nacho.wilder.exceptions.UserAlreadyExistsException;
import dev.nacho.wilder.services.RegisterService;

@RestController
@RequestMapping(path = "/api/v1/register")

public class RegisterController {
    RegisterService service;
    
    public RegisterController(RegisterService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<String> register(@RequestBody UserDto newUser) {
        try {
            return ResponseEntity.ok(service.save(newUser));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
       
    }
}