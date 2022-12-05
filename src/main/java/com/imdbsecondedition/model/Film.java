package com.imdbsecondedition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "film")
public class Film {

    private Long id;
    private String name;
    private Long country;
    private LocalDate releaseDate;
    private String description;
}
