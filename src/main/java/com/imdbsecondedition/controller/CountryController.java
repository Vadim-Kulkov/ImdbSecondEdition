package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Country;
import com.imdbsecondedition.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/all")
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Country findById(@PathVariable("id") Long id) {
        return countryRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody Country resource) {
        Objects.requireNonNull(resource);
        return countryRepository.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Country resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        countryRepository.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        countryRepository.deleteById(id);
    }
}
