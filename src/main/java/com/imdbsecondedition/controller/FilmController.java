package com.imdbsecondedition.controller;

import com.imdbsecondedition.dao.FilmDao;
import com.imdbsecondedition.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/films")
public class FilmController {

    private final FilmDao filmDao;

    @Autowired
    public FilmController(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @GetMapping("/all")
    public List<Film> getAll() {
        return filmDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Film findById(@PathVariable("id") Long id) {
        return filmDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Film resource) {
        Objects.requireNonNull(resource);
        return filmDao.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Film resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        filmDao.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        filmDao.deleteById(id);
    }
}
