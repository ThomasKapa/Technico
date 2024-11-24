package com.technicoCompany.technico.service;

import com.technicoCompany.technico.base.BaseComponent;
import com.technicoCompany.technico.exception.InvalidIdException;
import com.technicoCompany.technico.model.BaseModel;
import com.technicoCompany.technico.repository.BaseRepository;

import java.util.Arrays;
import java.util.List;


public abstract class BaseServiceImpl<T extends BaseModel> extends BaseComponent implements BaseServise<T, Long> {
	protected abstract BaseRepository< T, Long> getRepository();

	@Override
	public T create(final T item) {
		return getRepository().create(item);
	}

	@Override
	public List<T> createAll(final T... items) {
		return createAll(Arrays.asList(items));
	}

	@Override
	public List<T> createAll(final List<T> items) {
		return getRepository().createAll(items);
	}

	@Override
	public void update(final T item) {
		getRepository().update(item);

	}

	@Override
	public void delete(final T item) {
		getRepository().delete(item);
	}

	@Override
	public void deleteById(final Long id) {
		getRepository().deleteById(id);
	}

	@Override
	public T get(final Long id) {
		if (getRepository().get(id) == null) {
			throw new InvalidIdException(String.format("Resource with id [%d] not found", id));
		}
		return getRepository().get(id);
	}

	@Override
	public boolean exists(final T item) {
		return getRepository().exists(item);
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
