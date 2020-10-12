package com.hebert.bloodbank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hebert.bloodbank.model.Address;
import com.hebert.bloodbank.model.City;
import com.hebert.bloodbank.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	private static final long serialVersionUID = -87768589222248678L;
	
	private AddressRepository repository;
	
	public AddressServiceImpl(AddressRepository repository) {
		this.repository = repository;	
	}

	@Override
	public List<Address> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Address> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Address save(Address entity) {
		return repository.save(entity);
	}
	
	@Override
	public void delete(Address entity) {
		repository.delete(entity);
	}

	@Transactional
	@Override
	public void polulateAll(List<Address> entities) {
		repository.saveAll(entities);	
	}
	
	@Transactional
	@Override
	public Address getEntity(String street, String number, String district, String zip, City city) {
		Address entity = null;
		Optional<Address> found = repository.findByZipAndStreetAndNumber(zip, street, number);
		
		if (found.isPresent()) {
			entity = found.get();
		} else {
    		entity = new Address(street, number, district, zip, city);
    		save(entity);
		}	
		return entity;		
	}
}
