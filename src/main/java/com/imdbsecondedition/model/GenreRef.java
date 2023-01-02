package com.imdbsecondedition.model;

import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "film_genre", schema = "main")
public class GenreRef {

    @Column("genre_id")
    AggregateReference<Genre, Long> id;

    public GenreRef(Long id) {
        this.id = AggregateReference.to(id);
    }

    public Long getId() {
        return id.getId();
    }
}
