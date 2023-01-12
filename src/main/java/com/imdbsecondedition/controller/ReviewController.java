package com.imdbsecondedition.controller;

import com.imdbsecondedition.model.Review;
import com.imdbsecondedition.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Review resource) {
        Objects.requireNonNull(resource);
        return reviewRepository.save(resource).getId();
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Review resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        reviewRepository.save(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        reviewRepository.deleteById(id);
    }
}
