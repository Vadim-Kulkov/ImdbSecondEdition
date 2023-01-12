package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Genre;
import com.imdbsecondedition.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/genres")
public class GenreController {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping("/all")
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Genre findById(@PathVariable("id") Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody Genre resource) {
        Objects.requireNonNull(resource);
        return genreRepository.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Genre resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        genreRepository.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        genreRepository.deleteById(id);
    }
}
