package com.hebert.bloodbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hebert.bloodbank.model.State;
import com.hebert.bloodbank.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {
	private static final long serialVersionUID = -9103402997955256717L;
	
	private StateRepository repository;
	
	public StateServiceImpl(StateRepository repository) {
		this.repository = repository;	
	}

	@Override
	public List<State> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<State> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public State save(State entity) {
		return repository.save(entity);
	}
	
	@Override
	public void delete(State entity) {
		repository.delete(entity);
	}
	
	@Transactional
	@Override
	public void polulateAll(List<State> entities) {
		repository.saveAll(entities);	
	}
	
	@Transactional
	public State getEntity(String name) {
		State state = null;
		Optional<State> found = repository.findByName(name);
		
		if (found.isPresent()) {
			state = found.get();
		} else {
	  		state = new State(null, name); 
			state = save(state);
		}	
		return state;		
	}

}
