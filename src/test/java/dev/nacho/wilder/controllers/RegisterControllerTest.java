package dev.nacho.wilder.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import dev.nacho.wilder.dtos.UserDto;
import dev.nacho.wilder.exceptions.UserAlreadyExistsException;
import dev.nacho.wilder.services.RegisterService;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @Mock
    private RegisterService service;

    @InjectMocks
    private RegisterController controller;

    @Test
    void testRegister() {
        UserDto newUser = new UserDto();

        when(service.save(newUser)).thenReturn("Success");
        
        ResponseEntity<String> response = controller.register(newUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    void testRegister_alreadyExists() {
        UserDto newUser = new UserDto();

        when(service.save(newUser)).thenThrow(UserAlreadyExistsException.class);
        
        ResponseEntity<String> response = controller.register(newUser);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
