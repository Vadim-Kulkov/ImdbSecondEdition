package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.repository.CountryRepository;
import com.imdbsecondedition.repository.FilmGenreRepository;
import com.imdbsecondedition.repository.FilmRepository;
import com.imdbsecondedition.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/films")
@CrossOrigin
public class FilmController {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final FilmGenreRepository filmGenreRepository;

    @Autowired
    public FilmController(FilmRepository filmRepository, GenreRepository genreRepository, CountryRepository countryRepository, FilmGenreRepository filmGenreRepository) {
        this.filmRepository = filmRepository;
        this.genreRepository = genreRepository;
        this.countryRepository = countryRepository;
        this.filmGenreRepository = filmGenreRepository;
    }

    @GetMapping("/all")
    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Film findById(@PathVariable("id") Long id) {
        return filmRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody Film resource) {
        Objects.requireNonNull(resource);
        return filmRepository.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Film resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        filmRepository.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        filmRepository.deleteById(id);
    }
}
