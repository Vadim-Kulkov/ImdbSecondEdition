package com.imdbsecondedition.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "review")
public class Review {

    private Long id;
    private Long film;
    private Long reviewer;
    private String title;
    private String description;
}
