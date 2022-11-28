package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> all() {
        return jdbcTemplate.query("SELECT * FROM country", new BeanPropertyRowMapper<>(Country.class));
    }
}
