package com.hebert.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hebert.bloodbank.model.Donator;

@Repository
public interface DonatorRepository extends JpaRepository<Donator, Long> { 
}