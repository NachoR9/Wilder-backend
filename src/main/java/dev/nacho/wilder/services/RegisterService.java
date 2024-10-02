package dev.nacho.wilder.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import dev.nacho.wilder.dtos.UserDto;
import dev.nacho.wilder.implementations.IEncryptFacade;
import dev.nacho.wilder.models.Role;
import dev.nacho.wilder.models.User;
import dev.nacho.wilder.repositories.UserRepository;

@Service
public class RegisterService {

    UserRepository repository;
    RoleService roleService;
    IEncryptFacade encoderFacade;

    public RegisterService(UserRepository repository, RoleService roleService, IEncryptFacade encoderFacade) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoderFacade = encoderFacade;
    }

    public String save(UserDto newUserDto) {
        String passwordDecoded = encoderFacade.decode("base64", newUserDto.getPassword());
        String passwordEncoded = encoderFacade.encode("bcrypt", passwordDecoded);

        User user = new User();
        user.setUsername(newUserDto.getUsername());
        user.setPassword(passwordEncoded);
        user.setRoles(assignDefaultRole());
        repository.save(user);
        return "Success";
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L);
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        return roles;
    }
}