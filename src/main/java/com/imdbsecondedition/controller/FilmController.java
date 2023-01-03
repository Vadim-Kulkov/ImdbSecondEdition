package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Country;
import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.model.Genre;
import com.imdbsecondedition.repository.CountryRepository;
import com.imdbsecondedition.repository.FilmRepository;
import com.imdbsecondedition.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController()
@RequestMapping("/films")
public class FilmController {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository, GenreRepository genreRepository, CountryRepository countryRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.countryRepository = countryRepository;
    }

//    @GetMapping("/all")
//    public List<Film> getAll() {
//        return filmDao.findAll();
//    }

//    @GetMapping(value = "/{id}")
//    public Film findById(@PathVariable("id") Long id) {
//        return filmDao.findById(id);
//    }

    @GetMapping(value = "/byGenre/{genreId}")
    public Set<Film> findAllByGenre(@PathVariable(value = "genreId") Long genreId) {
        Country country = Country.create("USA");
        country = countryRepository.save(country);

        Film film = Film.create("Movie1", AggregateReference.to(country.getId()), LocalDate.now(), "..", null, null);
        Genre genre = Genre.create("Horror", "Horror genre", null);

        filmRepository.save(film);
        genre = genreRepository.save(genre);
        film.addGenre(genre);
        filmRepository.save(film);

        return filmRepository.findByGenreId(genreId);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public int create(@RequestBody Film resource) {
//        Objects.requireNonNull(resource);
//        return filmDao.create(resource);
//    }

//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") long id, @RequestBody Film resource) {
//        Objects.requireNonNull(resource);
//        resource.setId(id);
//        filmDao.update(resource);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") long id) {
//        filmDao.deleteById(id);
//    }
}
