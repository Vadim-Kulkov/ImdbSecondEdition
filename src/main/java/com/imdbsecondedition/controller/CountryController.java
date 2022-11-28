package com.imdbsecondedition.controller;

import com.imdbsecondedition.dao.CountryDao;
import com.imdbsecondedition.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/countries")
@CrossOrigin
public class CountryController {

    private final CountryDao countryDao;

    @Autowired
    public CountryController(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @GetMapping("/all")
    public List<Country> getAll() {
        return countryDao.all();
    }
}
