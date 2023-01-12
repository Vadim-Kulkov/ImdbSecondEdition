package com.imdbsecondedition.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "review", schema = "main")
public class Review {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "film", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reviewer")
    private Person reviewer;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

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
