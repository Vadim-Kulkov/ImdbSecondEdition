package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.FilmGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FilmGenreRepository extends JpaRepository<FilmGenre, Long> {
}
