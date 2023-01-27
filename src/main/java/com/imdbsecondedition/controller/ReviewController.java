package com.imdbsecondedition.controller;

import com.imdbsecondedition.dto.reviewDTO;
import com.imdbsecondedition.model.Review;
import com.imdbsecondedition.repository.FilmRepository;
import com.imdbsecondedition.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController()
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ReviewController(ReviewRepository reviewRepository, FilmRepository filmRepository) {
        this.reviewRepository = reviewRepository;
        this.filmRepository = filmRepository;
    }

    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @GetMapping(value = "/allByFilm/{id}")
    public Set<Review> findAllByFilm(@PathVariable("id") Long id) {
        return reviewRepository.findAllByFilm(filmRepository.findById(id).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody reviewDTO resource) {
        Objects.requireNonNull(resource);
        System.out.println(resource.getFilmId());
        Review review = new Review(null, filmRepository.getReferenceById(resource.getFilmId()), resource.getReviewer(), resource.getTitle(), resource.getDescription());
        return reviewRepository.save(review).getId();
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
