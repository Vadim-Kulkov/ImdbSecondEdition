package com.imdbsecondedition.repository;

import com.imdbsecondedition.model.Person;
import liquibase.pro.packaged.Q;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select object(p)\n" +
            "from Person p\n" +
            "         join FilmPerson fp on p.id = fp.person.id\n" +
            "         join Film f on f.id = fp.film.id\n" +
            "where fp.type = false and f.id = :id")
    List<Person> findAllProducersByFilm(@Param("id") Long id);

    @Query("select object(p)\n" +
            "from Person p\n" +
            "         join FilmPerson fp on p.id = fp.person.id\n" +
            "         join Film f on f.id = fp.film.id\n" +
            "where fp.type = true and f.id = :id")
    List<Person> findAllActorsByFilm(@Param("id") Long id);
}
