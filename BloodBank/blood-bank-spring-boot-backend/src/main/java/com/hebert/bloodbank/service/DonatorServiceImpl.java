package com.hebert.bloodbank.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.enums.GenderTypes;
import com.hebert.bloodbank.model.Address;
import com.hebert.bloodbank.model.City;
import com.hebert.bloodbank.model.Donator;
import com.hebert.bloodbank.model.State;
import com.hebert.bloodbank.repository.DonatorRepository;

@Service
public class DonatorServiceImpl implements DonatorService {
	private static final long serialVersionUID = -1603925162191880597L;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonatorServiceImpl.class);
	
	private DonatorRepository repository;
	private StateService stateService;
	private CityService cityService;
	private AddressService addressService;
	
	public DonatorServiceImpl(DonatorRepository repository, StateServiceImpl stateService, CityServiceImpl cityService, AddressServiceImpl addressService) {
		this.stateService = stateService;
		this.cityService = cityService;
		this.addressService = addressService;
		this.repository = repository;	
	}

	@Override
	public List<Donator> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Donator> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Donator save(Donator entity) {
		return saveDonator(entity);
	}
	
	@Override
	public void delete(Donator entity) {
		repository.delete(entity);
	}
	
	@Transactional
	@Override
	public void polulateAll(List<Donator> entities) {
		repository.saveAll(entities);	
	}

	@Transactional
	@Override
	public Donator getEntity(String name, String cpf, String identity, String email, GenderTypes gender,
			BloodTypes bloodType, BigDecimal weight, BigDecimal height, String father, String mother, LocalDate birthDate,
			Address address) {
		Donator donator = new Donator(name, cpf, identity, email, gender, bloodType, weight, height, father, mother, birthDate, address);	
		return repository.save(donator);
	}
	
	private Donator saveDonator(Donator donator) {			
		LOGGER.info("Inserting a donator {}", donator);
      	
		Address address = donator.getAddress();
		City city = address.getCity();
		State state = city.getState();
		
		state = this.stateService.getEntity(state.getName());  		
		city = this.cityService.getEntity(city.getName(), state);    		
		address = this.addressService.getEntity(address.getStreet(), address.getNumber(), address.getDistrict(), address.getZip(), city);		
		donator = this.getEntity(
				donator.getName(), 
				donator.getCpf(), 
				donator.getIdentity(), 
				donator.getEmail(), 
				donator.getGender(),
				donator.getBloodType(),
				donator.getWeight(),
				donator.getHeight(),
				donator.getFather(),
				donator.getFather(), 
				donator.getBirthDate(),
				address);
		
		LOGGER.info("Inserted a donator {} successfully", donator);
		
		return donator;
    	
	}
}
