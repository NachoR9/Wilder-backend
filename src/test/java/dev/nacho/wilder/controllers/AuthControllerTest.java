package dev.nacho.wilder.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
public class AuthControllerTest {

    private AuthController authController = new AuthController();

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testLogin() {
        ResponseEntity<Map<String, String>> response = authController.login();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Logged", response.getBody().get("message"));
        assertEquals("user", response.getBody().get("username"));
        assertEquals("ROLE_USER", response.getBody().get("roles"));
    }
}
