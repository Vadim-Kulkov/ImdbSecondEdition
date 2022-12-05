package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Genre;
import com.imdbsecondedition.service.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GenreDao implements Dao<Genre> {

    private static final String FIND_BY_ID = "select * from genre where id = ?";
    private static final String DELETE_GENRE = "delete from genre where id = ?";
    private static final String UPDATE_GENRE = "update genre set name = ?, description = ? where id = ?";
    private static final String GET_ALL = "select * from genre";
    private static final String INSERT_GENRE = "insert into genre(name, description) values(?, ?)";

    private static final String NOT_NULL_MESSAGE = "A genre object to save must not be null";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> findAll() {
        return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Genre.class));
    }

    public Genre findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Genre.class), id);
    }

    public int create(Genre genre) {
        Objects.requireNonNull(genre, NOT_NULL_MESSAGE);
        return jdbcTemplate.update(INSERT_GENRE, genre.getName(), genre.getDescription());
    }

    public void update(Genre genre) {
        Objects.requireNonNull(genre, NOT_NULL_MESSAGE);
        jdbcTemplate.update(UPDATE_GENRE, genre.getName(), genre.getDescription(), genre.getId());
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_GENRE, id);
    }
}
