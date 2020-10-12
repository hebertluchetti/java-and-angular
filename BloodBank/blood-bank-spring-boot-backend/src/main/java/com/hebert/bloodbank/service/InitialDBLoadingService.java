package com.hebert.bloodbank.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.enums.GenderTypes;
import com.hebert.bloodbank.model.Address;
import com.hebert.bloodbank.model.City;
import com.hebert.bloodbank.model.Donor;
import com.hebert.bloodbank.model.State;
import com.hebert.bloodbank.model.dto.DonorJsonDTO;
import com.hebert.bloodbank.reader.JsonRead;

@Service
public class InitialDBLoadingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitialDBLoadingService.class);
	
	private DonorService donatorService;
	private StateService stateService;
	private CityService cityService;
	private AddressService addressService;
	
	public InitialDBLoadingService(DonorServiceImpl donatorService, StateServiceImpl stateService, CityServiceImpl cityService, AddressServiceImpl addressService) {
		this.donatorService = donatorService;	
		this.stateService = stateService;
		this.cityService = cityService;
		this.addressService = addressService;
		initializeDB();
	}
	
	private void initializeDB() {
		String path = "./doc/data.json";
		List<Donor> list = this.donatorService.findAll();		
		
		if (Objects.isNull(list) || list.isEmpty()) {
			LOGGER.info("Importing the donator JSON file {} ", path);
		    polulateDonatorPersistence(path);
		}	
	}
	
	private void polulateDonatorPersistence(String jsonFilePath) {
        try {
        	List<DonorJsonDTO> list = JsonRead.readListFrom(jsonFilePath);
        	LOGGER.info("The donator JSON file {} loaded successfully", jsonFilePath);	        	
        	//list.stream().forEach(item-> System.out.println(item.getData_nasc()));
        	polulatePersistence(list);	       			        	
        	//donators.stream().forEach(System.out::println);
        	LOGGER.info("The Donor persistence was populated successfully");
        } catch (NoSuchFileException e) {
        	LOGGER.error("The json file was not found in directory {} ", jsonFilePath);
        } catch (IOException e) {
        	LOGGER.error("Error {} ", e.getMessage());
        }
    }
	
	private void polulatePersistence(List<DonorJsonDTO> dtos) {		
		for (DonorJsonDTO item: dtos) {   
    		LocalDate date = LocalDate.now();
    		String strDate = item.getData_nasc();
      		BigDecimal weight = new BigDecimal(item.getPeso());
    		BigDecimal height = new BigDecimal(item.getAltura());
    		
    		LOGGER.info("Inserting an item {}", item);
    		    		
    		if (Objects.nonNull(strDate) && !strDate.isEmpty()) {
    	  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        		date = LocalDate.parse(strDate, formatter);
    		}
    		
    		BloodTypes bloodType= BloodTypes.getByType(item.getTipo_sanguineo());
    		GenderTypes gender = GenderTypes.getByType(item.getSexo());
    		
    		State state = this.stateService.getEntity(item.getEstado());
    		City city = this.cityService.getEntity(item.getCidade(), state);    		
    		Address address = this.addressService.getEntity(item.getEndereco(), item.getNumero(), item.getBairro(), item.getCep(), city);		
    		this.donatorService.getEntity(
    				item.getNome(), 
    				item.getCpf(), 
    				item.getRg(), 
    				item.getEmail(), 
    				gender,
    				bloodType,
    				weight,
    				height,
    				item.getPai(),
    				item.getMae(), 
    				date,
    				address);
    	}
	}
	
	private Boolean polulateStateDB(Set<State> states) {
		List<State> list = this.stateService.findAll();
		
		if (Objects.isNull(list) || list.isEmpty()) {
			LOGGER.info("Populating the State persistence");
	    	List<State> sts = new ArrayList<>(states);
			this.stateService.polulateAll(sts);
			return true;
		}
		LOGGER.error("The State persistence already exists");
		return false;
	}
	
	private Boolean polulateCities(Set<City> cities) {
		List<City> list = this.cityService.findAll();
		
		if (Objects.isNull(list) || list.isEmpty()) {
			LOGGER.info("Populating the City persistence");
			List<City> cts = new ArrayList<>(cities);
			this.cityService.polulateAll(cts);
			return true;
		}
		LOGGER.error("The City persistence already exists");		
		return false;
	}
	
	private Boolean polulateAddresses(List<Address> addresses) {
		List<Address> list = this.addressService.findAll();
		
		if (Objects.isNull(list) || list.isEmpty()) {
			LOGGER.info("Populating the Address persistence");
			this.addressService.polulateAll(addresses);
			return true;
		}
		LOGGER.error("The Address persistence already exists");		
		return false;
	}
		
	private Boolean polulateDonators(List<Donor> donators) {
		List<Donor> list = this.donatorService.findAll();
		
		if (Objects.isNull(list) || list.isEmpty()) {			
			LOGGER.info("Populating the Donor persistence");
			this.donatorService.polulateAll(donators);
			return true;
		}
		LOGGER.error("The Donor persistence already exists");
		return false;
	}

	private void polulateDonatorDB(Set<State> states, Set<City> cities, List<Address> addresses, List<Donor> donators) {		
		Boolean success = polulateStateDB(states);
		
		if (success) {
			success = polulateCities(cities);
		}

		if (success) {
			success = polulateAddresses(addresses);
		}

		if (success) {
			success = polulateDonators(donators);
		}
	}
	
		 
}
