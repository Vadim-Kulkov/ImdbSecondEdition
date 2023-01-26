package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Set<Review> findAllByFilm(Film film);
}
