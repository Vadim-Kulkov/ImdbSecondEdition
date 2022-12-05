package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.service.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FilmDao implements Dao<Film> {

    private static final String FIND_BY_ID = "select * from film where id = ?";
    private static final String DELETE_FILM = "delete from film where id = ?";
    private static final String UPDATE_FILM = "update film set name = ?, country = ?, release_date = ?, description = ? where id = ?";
    private static final String GET_ALL = "select * from film";
    private static final String INSERT_FILM = "insert into film(name, country, release_date, description) values(?, ?, ?, ?)";

    private static final String NOT_NULL_MESSAGE = "A film object to save must not be null";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FilmDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Film> findAll() {
        return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Film.class));
    }

    @Override
    public Film findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Film.class), id);
    }

    @Override
    public int create(Film resource) {
        Objects.requireNonNull(resource, NOT_NULL_MESSAGE);
        return jdbcTemplate.update(INSERT_FILM, resource.getName(), resource.getCountry(), resource.getReleaseDate(), resource.getDescription());
    }

    @Override
    public void update(Film resource) {
        Objects.requireNonNull(resource, NOT_NULL_MESSAGE);
        jdbcTemplate.update(UPDATE_FILM, resource.getName(), resource.getCountry(), resource.getReleaseDate(), resource.getDescription(), resource.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_FILM, id);
    }
}
