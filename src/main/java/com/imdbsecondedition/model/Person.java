package com.imdbsecondedition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "person", schema = "main")
public class Person {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Boolean gender;
    private Country homeCountry;
    private String biography;

    public static Person create(String firstName, String lastName, String patronymic, LocalDate birthDate, LocalDate deathDate, Boolean gender, Country homeCountry, String biography) {
        return new Person(null, firstName, lastName, patronymic, birthDate, deathDate, gender, homeCountry, biography);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", patronymic='").append(patronymic).append('\'');
        sb.append(", birthDate=").append(birthDate);
        sb.append(", deathDate=").append(deathDate);
        sb.append(", gender=").append(gender);
        sb.append(", homeCountry=").append(homeCountry);
        sb.append(", biography='").append(biography).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
