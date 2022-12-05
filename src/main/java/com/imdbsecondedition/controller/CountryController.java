package com.imdbsecondedition.controller;

import com.imdbsecondedition.dao.CountryDao;
import com.imdbsecondedition.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/countries")
class CountryController {

    private final CountryDao countryDao;

    @Autowired
    public CountryController(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping("/all")
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Country findById(@PathVariable("id") Long id) {
        return countryDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Country resource) {
        Objects.requireNonNull(resource);
        return countryDao.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Country resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        countryDao.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        countryDao.deleteById(id);
    }
}
