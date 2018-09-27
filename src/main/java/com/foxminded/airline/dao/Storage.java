package com.foxminded.airline.dao;

import java.util.List;

public interface Storage<T> {
    void save(T t);

    void update(T t);

    void delete(T t);

    T findById(int id);

    List<T> getAll();
}
