package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Review;
import com.imdbsecondedition.service.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewDao implements Dao<Review> {

    private static final String FIND_BY_ID = "select * from review where id = ?";
    private static final String DELETE_REVIEW = "delete from review where id = ?";
    private static final String UPDATE_REVIEW = "update review set name = ?, reviewer = ? where id = ?";
    private static final String GET_ALL = "select * from review";
    private static final String INSERT_REVIEW = "insert into review(film, reviewer, title, description) values(?, ?, ?, ?)";

    private static final String NOT_NULL_MESSAGE = "A review object to save must not be null";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Review> findAll() {
        return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Review.class));
    }

    public Review findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Review.class), id);
    }

    public int create(Review review) {
        Objects.requireNonNull(review, NOT_NULL_MESSAGE);
        return jdbcTemplate.update(INSERT_REVIEW, review.getFilm(), review.getReviewer(), review.getTitle(), review.getDescription());
    }

    public void update(Review review) {
        Objects.requireNonNull(review, NOT_NULL_MESSAGE);
        jdbcTemplate.update(UPDATE_REVIEW, review);
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_REVIEW, id);
    }
}
