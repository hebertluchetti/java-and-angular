package com.hebert.bloodbank.service;

import java.util.List;

import com.hebert.bloodbank.model.Address;
import com.hebert.bloodbank.model.City;

public interface AddressService extends GenericService<Long, Address> {	
	public void polulateAll(List<Address> entities);
	public Address getEntity(String street, String number, String district, String zip, City city);
}
