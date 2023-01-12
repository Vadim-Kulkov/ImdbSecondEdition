package com.imdbsecondedition.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "genre", schema = "main")
public class Genre {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<FilmGenre> films;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id.equals(genre.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
