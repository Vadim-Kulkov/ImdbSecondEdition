package com.imdbsecondedition.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imdbsecondedition.model.Film;
import com.imdbsecondedition.model.Person;
import lombok.Data;

import javax.persistence.*;

@Data
public class reviewDTO {

    private Long id;

    private Long filmId;

    private Person reviewer;

    private String title;

    private String description;

}
