package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.model.Genre;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
