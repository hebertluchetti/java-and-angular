package com.hebert.bloodbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hebert.bloodbank.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> { 
	public Optional<State> findByName(String name);
}