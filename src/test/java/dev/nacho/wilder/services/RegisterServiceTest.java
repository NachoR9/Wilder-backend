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

import dev.nacho.wilder.dtos.UserDto;
import dev.nacho.wilder.exceptions.UserAlreadyExistsException;
import dev.nacho.wilder.implementations.IEncryptFacade;
import dev.nacho.wilder.models.Role;
import dev.nacho.wilder.models.User;
import dev.nacho.wilder.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class RegisterServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private RoleService roleService;
    @Mock
    private IEncryptFacade encoderFacade;

    @InjectMocks
    private RegisterService service;

    @Test
    void testSave() {
        UserDto userDto = new UserDto();
        userDto.setUsername("test-user");
        userDto.setPassword("test-password");

        when(encoderFacade.decode("base64", "test-password")).thenReturn("decoded");
        when(encoderFacade.encode("bcrypt", "decoded")).thenReturn("encrypted");

        Role defaultRole = new Role();
        defaultRole.setId(1L);
        defaultRole.setName("USER");
        when(roleService.getById(1L)).thenReturn(defaultRole);

        String result = service.save(userDto);
        assertEquals("Success", result);
    }

    @Test
    void testSave_exists() {
        UserDto userDto = new UserDto();
        userDto.setUsername("test-user");
        userDto.setPassword("test-password");

        when(repository.findByUsername("test-user")).thenReturn(Optional.of(new User()));

        assertThrows(UserAlreadyExistsException.class, () -> service.save(userDto));
    }
}
