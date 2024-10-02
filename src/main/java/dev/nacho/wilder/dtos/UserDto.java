package dev.nacho.wilder.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto { 

    @NotBlank
    private String username;
    @NotBlank
    private String password;       
    
}