package com.hebert.bloodbank.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenericService<ID, T> extends Serializable {
	public List<T> findAll();
	public Optional<T> findById(ID id);
	public T save(T entity);
	public void delete(T entity);
}
