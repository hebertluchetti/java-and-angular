package com.hebert.bloodbank.service;

import java.util.List;

import com.hebert.bloodbank.model.State;

public interface StateService extends GenericService<Long, State> {
	public void polulateAll(List<State> entities);
	public State getEntity(String name);
}
