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


    @Query("select f.* from film f join film_genre fg on f.id = fg.film_id where fg.genre_id = :id")
    Set<Film> findByFilmId(@Param("id") Long id);
}
