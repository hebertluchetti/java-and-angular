package com.hebert.bloodbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hebert.bloodbank.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> { 
	public Optional<Address> findByZipAndStreetAndNumber(String zip, String street, String number);
}