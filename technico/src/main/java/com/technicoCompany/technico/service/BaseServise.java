package com.technicoCompany.technico.service;

import com.technicoCompany.technico.model.BaseModel;

import java.util.List;

public interface BaseServise<T extends BaseModel, K> {

    T create(T item);

    List<T> createAll(List<T> items);

    List<T> createAll(T... items);

    void update(T item);

    void delete(T item);

    void findById(K id);

    void deleteById(K id);

    T get(K id);

    boolean exists(T item);

    List<T> findAll();

    Long count();
}
