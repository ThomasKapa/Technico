package com.technicoCompany.technico.repository;

import com.technicoCompany.technico.model.BaseModel;
import com.technicoCompany.technico.model.Property;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends BaseModel, K> {
	T create(T item);

	List<T> createAll(List<T> items);

	List<T> createAll(T... items);

	void update(T item);

	void delete(T item);

	void deleteById(K id);

	T get(K id);

	boolean exists(T item);

	List<T> findAll();

	Long count();

	Optional<T> findById(K id);
}
