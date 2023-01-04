package com.imdbsecondedition.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "film", schema = "main")
public class Film {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;

    private LocalDate releaseDate;

    private String description;

    private String image;

    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<FilmGenre> genres;

    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id.equals(film.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
