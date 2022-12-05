package com.imdbsecondedition.controller;

import com.imdbsecondedition.dao.ReviewDao;
import com.imdbsecondedition.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewDao reviewDao;

    @Autowired
    public ReviewController(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @GetMapping("/all")
    public List<Review> getAll() {
        return reviewDao.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review findById(@PathVariable("id") Long id) {
        return reviewDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody Review resource) {
        Objects.requireNonNull(resource);
        return reviewDao.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody Review resource) {
        Objects.requireNonNull(resource);
        resource.setId(id);
        reviewDao.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") long id) {
        reviewDao.deleteById(id);
    }
}
