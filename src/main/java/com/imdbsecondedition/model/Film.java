package com.imdbsecondedition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "film", schema = "main")
public class Film {

    @Id
    private Long id;
    private String name;
    private AggregateReference<Country, Long> country;
    private LocalDate releaseDate;
    private String description;
    private String image;
    @MappedCollection(idColumn = "film_id")
    private Set<GenreRef> genres;

    public static Film create(String name, AggregateReference<Country, Long> country, LocalDate releaseDate, String description, String image, Set<GenreRef> genres) {
        return new Film(null, name, country, releaseDate, description, image, genres);
    }

    public void addGenre(Genre genre) {
        genres.add(new GenreRef(genre.getId()));
    }

    public Set<Long> getGenreIds() {
        return genres.stream().map(GenreRef::getId).collect(Collectors.toSet());
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Film{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", country=").append(country);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", description='").append(description).append('\'');
        sb.append(", image='").append(image).append('\'');
        sb.append(", genres=").append(genres);
        sb.append('}');
        return sb.toString();
    }
}
