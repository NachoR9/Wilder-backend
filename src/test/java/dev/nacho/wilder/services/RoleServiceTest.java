package dev.nacho.wilder.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.nacho.wilder.models.Role;
import dev.nacho.wilder.repositories.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository repository;

    @InjectMocks
    private RoleService service;

    @Test
    void testGetById() {
        Role role = new Role();
        role.setId(2L);
        role.setName("ADMIN");

        when(repository.findById(2L)).thenReturn(Optional.of(role));

        Role result = service.getById(2L);
        assertEquals(role, result);
    }

    @Test
    void testGetById_notFound() {
        when(repository.findById(87L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getById(87L));
    }
}
