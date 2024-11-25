package com.technicoCompany.technico.service;

import com.technicoCompany.technico.base.BaseComponent;
import com.technicoCompany.technico.exception.InvalidIdException;
import com.technicoCompany.technico.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;


public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseServise<T, Long> {
    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    public T create(final T item) {
        return getRepository().save(item);
    }

    @Override
    public List<T> createAll(final T... items) {
        return createAll(Arrays.asList(items));
    }

    @Override
    public List<T> createAll(final List<T> items) {
        return getRepository().saveAll(items);
    }

    @Override
    public void update(final T item) {
        getRepository().save(item);

    }

    @Override
    public void delete(final T item) {
        getRepository().delete(item);
    }

    @Override
    public void deleteById(final Long id) {
        getRepository().deleteById(id);
    }

    public void findById(final Long id) {getRepository().findById(id);}

    @Override
    public T get(final Long id) {
        if (getRepository().getById(id) == null) {
            throw new InvalidIdException(String.format("Resource with id [%d] not found", id));
        }
        return getRepository().getById(id);
    }

    @Override
    public boolean exists(final T item) {
        return false;
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public Long count() {
        return getRepository().count();
    }



}
