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
@Table(name = "person")
public class Person {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Boolean gender;
    private Long homeCountry;
    private String biography;
}
