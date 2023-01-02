package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/genres")
public class GenreController {

//    private final GenreDao genreDao;
//
//    @Autowired
//    public GenreController(GenreDao genreDao) {
//        this.genreDao = genreDao;
//    }
//
//    @GetMapping("/all")
//    public List<Genre> getAll() {
//        return genreDao.findAll();
//    }
//
//    @GetMapping(value = "/{id}")
//    public Genre findById(@PathVariable("id") Long id) {
//        return genreDao.findById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public int create(@RequestBody Genre resource) {
//        Objects.requireNonNull(resource);
//        return genreDao.create(resource);
//    }
//
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void update(@PathVariable("id") long id, @RequestBody Genre resource) {
//        Objects.requireNonNull(resource);
//        resource.setId(id);
//        genreDao.update(resource);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable("id") long id) {
//        genreDao.deleteById(id);
//    }
}
