package com.imdbsecondedition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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

    @Column(name = "name", nullable = false)
    private String name;

    @JoinColumn(name = "country")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Country country;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<FilmGenre> genres;

    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @ToString.Exclude
    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<FilmPerson> persons;

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
