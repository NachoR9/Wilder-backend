package dev.nacho.wilder.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class VideogameDto {
    private Long id;
    private String name;
    private List<String> genres;
    private LocalDate releaseDate;
    private String company;
    private String platform;
}
