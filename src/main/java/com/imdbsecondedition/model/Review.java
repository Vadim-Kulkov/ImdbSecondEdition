package com.imdbsecondedition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Table(name = "review", schema = "main")
public class Review {

    @Id
    private Long id;
    private Film film;
    private Person reviewer;
    private String title;
    private String description;

    public static Review create(Film film, Person reviewer, String title, String description) {
        return new Review(null, film, reviewer, title, description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
