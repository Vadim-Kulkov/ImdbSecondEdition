package com.imdbsecondedition.dao;

import com.imdbsecondedition.model.Person;
import com.imdbsecondedition.service.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PersonDao implements Dao<Person> {

    private static final String FIND_BY_ID = "select * from person where id = ?";
    private static final String DELETE_FILM = "delete from person where id = ?";
    private static final String UPDATE_FILM = "update person set first_name = ?, last_name = ?, patronymic = ?, birth_date = ?, death_date = ?, gender = ?, home_country = ?, biography = ? where id = ?";
    private static final String GET_ALL = "select * from person";
    private static final String INSERT_FILM = "insert into person(first_name, last_name, patronymic, birth_date, death_date, gender, home_country, biography) values(?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String NOT_NULL_MESSAGE = "A person object to save must not be null";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(GET_ALL, new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person findById(long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new BeanPropertyRowMapper<>(Person.class), id);
    }

    @Override
    public int create(Person resource) {
        Objects.requireNonNull(resource, NOT_NULL_MESSAGE);
        return jdbcTemplate.update(INSERT_FILM, resource.getFirstName(), resource.getLastName(), resource.getPatronymic(), resource.getBirthDate(), resource.getDeathDate(), resource.getGender(), resource.getHomeCountry(), resource.getBiography());
    }

    @Override
    public void update(Person resource) {
        Objects.requireNonNull(resource, NOT_NULL_MESSAGE);
        jdbcTemplate.update(UPDATE_FILM, resource.getFirstName(), resource.getLastName(), resource.getPatronymic(), resource.getBirthDate(), resource.getDeathDate(), resource.getHomeCountry(), resource.getBiography());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update(DELETE_FILM, id);
    }
}
