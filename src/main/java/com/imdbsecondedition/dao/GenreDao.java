package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Country;
import com.imdbsecondedition.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Genre> all() {
        return jdbcTemplate.query("SELECT * FROM genre", new BeanPropertyRowMapper<>(Genre.class));
    }
}
