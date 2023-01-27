package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.model.FilmGenre;

import com.imdbsecondedition.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;

@Repository
@Transactional
public interface FilmRepository extends JpaRepository<Film, Long> {

    Set<FilmGenre> findAllByGenresIn(Collection<FilmGenre> genres);

}
