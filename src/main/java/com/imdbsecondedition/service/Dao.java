package com.imdbsecondedition.service;

import java.util.List;

public interface Dao<T> {

    List<T> findAll();

    T findById(long id);

    int create(T resource);

    void update(T resource);

    void deleteById(long id);
}
