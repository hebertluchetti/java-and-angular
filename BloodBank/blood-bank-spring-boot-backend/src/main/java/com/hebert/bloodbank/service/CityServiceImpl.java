package com.hebert.bloodbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hebert.bloodbank.model.City;
import com.hebert.bloodbank.model.State;
import com.hebert.bloodbank.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
	private static final long serialVersionUID = 1654772570952632300L;
	
	private CityRepository repository;

	public CityServiceImpl(CityRepository repository) {
		this.repository = repository;	
	}

	@Override
	public List<City> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<City> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public void delete(City entity) {
		repository.delete(entity);
	}

	@Override
	public City save(City entity) {
		return repository.save(entity);
	}
	
	@Transactional
	@Override
	public void polulateAll(List<City> entities) {
		repository.saveAll(entities);	
	}
	
	@Transactional
	public City getEntity(String name, State state) {
		City entity = null;
		Optional<City> found = repository.findByName(name);
		
		if (found.isPresent()) {
			entity = found.get();
		} else {
    		entity = new City(null, name, state);
    		save(entity);
		}	
		return entity;		
	}

}
