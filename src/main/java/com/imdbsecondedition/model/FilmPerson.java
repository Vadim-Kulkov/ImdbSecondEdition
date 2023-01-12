package com.imdbsecondedition.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "film_person", schema = "main")
public class FilmPerson {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "film_id")
    Film film;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    Person person;

    @Column(name = "type")
    Boolean type;
}
