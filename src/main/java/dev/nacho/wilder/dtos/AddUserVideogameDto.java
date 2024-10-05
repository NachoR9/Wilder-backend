package dev.nacho.wilder.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddUserVideogameDto {
    @NotNull
    private Long id;
}
