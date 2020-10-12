package com.hebert.bloodbank.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.enums.GenderTypes;
import com.hebert.bloodbank.model.Address;
import com.hebert.bloodbank.model.Donator;

public interface DonatorService extends GenericService<Long, Donator> {
	public void polulateAll(List<Donator> entities);
	public Donator getEntity(String name, String cpf, String identity, String email, GenderTypes gender, BloodTypes bloodType,
			BigDecimal weight, BigDecimal height, String father, String mother, LocalDate birthDate, Address address);	
}
