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
import com.hebert.bloodbank.model.Donor;
import com.hebert.bloodbank.model.State;
import com.hebert.bloodbank.repository.DonorRepository;

@Service
public class DonorServiceImpl implements DonorService {
	private static final long serialVersionUID = -1603925162191880597L;

	private static final Logger LOGGER = LoggerFactory.getLogger(DonorServiceImpl.class);
	
	private DonorRepository repository;
	private StateService stateService;
	private CityService cityService;
	private AddressService addressService;
	
	public DonorServiceImpl(DonorRepository repository, StateServiceImpl stateService, CityServiceImpl cityService, AddressServiceImpl addressService) {
		this.stateService = stateService;
		this.cityService = cityService;
		this.addressService = addressService;
		this.repository = repository;	
	}

	@Override
	public List<Donor> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Donor> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Donor save(Donor entity) {
		return saveDonator(entity);
	}
	
	@Override
	public void delete(Donor entity) {
		repository.delete(entity);
	}
	
	@Transactional
	@Override
	public void polulateAll(List<Donor> entities) {
		repository.saveAll(entities);	
	}

	@Transactional
	@Override
	public Donor getEntity(String name, String cpf, String identity, String email, GenderTypes gender,
			BloodTypes bloodType, BigDecimal weight, BigDecimal height, String father, String mother, LocalDate birthDate,
			Address address) {
		Donor donator = new Donor(name, cpf, identity, email, gender, bloodType, weight, height, father, mother, birthDate, address);	
		return repository.save(donator);
	}
	
	private Donor saveDonator(Donor donator) {			
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
