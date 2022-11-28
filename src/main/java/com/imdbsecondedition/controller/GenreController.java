package com.imdbsecondedition.controller;

import com.imdbsecondedition.dao.GenreDao;
import com.imdbsecondedition.model.Country;
import com.imdbsecondedition.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

    private final GenreDao genreDao;

    @Autowired
    public GenreController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @GetMapping("/all")
    public List<Genre> getAll() {
        return genreDao.all();
    }
}
