package dev.nacho.wilder.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateVideogameDto {

    @NotBlank
    private String name;
    @NotEmpty
    private List<Long> genres;
    @NotNull
    private LocalDate releaseDate;
    @NotBlank
    private String company;
    @NotBlank
    private String platform;

    private String image;
}
