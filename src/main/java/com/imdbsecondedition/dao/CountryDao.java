package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Country;
import com.imdbsecondedition.service.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryDao implements Dao<Country> {

    private static final String FIND_BY_ID = "select * from country where id = ?";
    private static final String DELETE_COUNTRY = "delete from country where id = ?";
    private static final String UPDATE_COUNTRY = "update country set name = ? where id = ?";
    private static final String GET_ALL = "select * from country";
    private static final String INSERT_COUNTRY = "insert into country(name) values(?)";

    private static final String NOT_NULL_MESSAGE = "A country object to save must not be null";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Country> findAll() {
        return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Country.class));
    }

    public Country findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Country.class), id);
    }

    public int create(Country country) {
        Objects.requireNonNull(country, NOT_NULL_MESSAGE);
        return jdbcTemplate.update(INSERT_COUNTRY, country.getName());
    }

    public void update(Country country) {
        Objects.requireNonNull(country, NOT_NULL_MESSAGE);
        jdbcTemplate.update(UPDATE_COUNTRY, country);
    }

    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_COUNTRY, id);
    }
}