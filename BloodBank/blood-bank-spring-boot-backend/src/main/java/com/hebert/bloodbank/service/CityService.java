package com.hebert.bloodbank.service;

import java.util.List;

import com.hebert.bloodbank.model.City;
import com.hebert.bloodbank.model.State;

public interface CityService extends GenericService<Long, City> {
	public void polulateAll(List<City> entities);
	public City getEntity(String name, State state);
}
