package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Person;
import com.imdbsecondedition.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@CrossOrigin
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable("id") Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public long create(@RequestBody Person resource) {
        Objects.requireNonNull(resource);
        return personRepository.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Person resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        personRepository.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        personRepository.deleteById(id);
    }

    @GetMapping(value = "/producersByFilm/{id}")
    public List<Person> getProducersByFilm(@PathVariable("id") long id) {
        return personRepository.findAllProducersByFilm(id);
    }

    @GetMapping(value = "/actorsByFilm/{id}")
    public List<Person> getActorsByFilm(@PathVariable("id") long id) {
        return personRepository.findAllActorsByFilm(id);
    }
}
