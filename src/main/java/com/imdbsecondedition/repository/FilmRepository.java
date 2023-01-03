package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Film;
import liquibase.pro.packaged.T;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

    @Query("select f.* from film f join film_genre fg on f.id = fg.film_id where fg.genre_id = :id")
    Set<Film> findByGenreId(@Param("id") Long id);

    @Query("update film set :property = :newValue where id = :id")
    void update(@Param("id") Long id, @Param("property") String property, @Param("newValue") T newValue);
}
