package dev.nacho.wilder.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.nacho.wilder.models.Role;
import dev.nacho.wilder.models.User;
import dev.nacho.wilder.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class JpaUserDetailsServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private JpaUserDetailsService service;

    @Test
    void testLoadUserByUsername() {
        User user = new User();
        user.setId(7L);
        user.setUsername("test-user");
        user.setPassword("test-password");
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        user.setRoles(Set.of(role));
        
        when(repository.findByUsername("test-user")).thenReturn(Optional.of(user));

        UserDetails userDetails = service.loadUserByUsername("test-user");

        assertEquals("test-user", userDetails.getUsername());
        assertEquals("test-password", userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    void testLoadUserByUsername_notFound() {
        when(repository.findByUsername("test-user")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("test-user"));
    }
}
