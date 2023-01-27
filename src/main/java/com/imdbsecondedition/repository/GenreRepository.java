package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findById(Long id);
}
