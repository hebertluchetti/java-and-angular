package com.hebert.bloodbank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonatorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonatorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByStateDTO;
import com.hebert.bloodbank.repository.DonatorDashboardRepository;

@Service
public class DonatorDashboardServiceImpl implements DonatorDashboardService {
	
	private DonatorDashboardRepository repository;
	
	public DonatorDashboardServiceImpl(DonatorDashboardRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DonatorQuantityByStateDTO> findDonatorQuantityByState() {
		return repository.findDonatorQuantityByState();
	}

	@Override
	public List<DonatorAgeAvgByBloodTypeDTO> findDonatorAgeAvgByBloodType() {
		return repository.findDonatorAgeAvgByBloodType();
	}

	@Override
	public List<DonatorImcAvgByAgeRangeDTO> findDonatorImcAvgByAgeRange() {
		return repository.findDonatorImcAvgByAgeRange();
	}
	
	@Override
	public List<DonatorImcPercentageByGenderDTO> findDonatorObesityPercentageByGender() {
		BigDecimal imcValue = new BigDecimal(30); // IMC > 30 determines the person is obese
		return repository.findDonatorImcPercentageByGender(imcValue);
	}
	
	@Override
	public List<DonatorQuantityByBloodTypeDTO> findDonatorQuantityByBloodType() {
		return repository.findDonatorQuantityByBloodType();
	}

	@Override
	public List<DonatorForBloodTypeDTO> findDonatorsForEachBloodType() {
		BigDecimal minWeight = new BigDecimal(50);  
		Integer minAge = Integer.valueOf(16);
		Integer maxAge = Integer.valueOf(69);
		
		List<Integer> fromAp = BloodTypes.A_POS.getOrdinalsReceiveFrom();
		List<Integer> fromAn = BloodTypes.A_NEG.getOrdinalsReceiveFrom(); 
		List<Integer> fromBp = BloodTypes.B_POS.getOrdinalsReceiveFrom(); 
		List<Integer> fromBn = BloodTypes.B_NEG.getOrdinalsReceiveFrom();
		List<Integer> fromABp = BloodTypes.AB_POS.getOrdinalsReceiveFrom();
		List<Integer> fromABn = BloodTypes.AB_NEG.getOrdinalsReceiveFrom(); 
		List<Integer> fromOp = BloodTypes.O_POS.getOrdinalsReceiveFrom(); 
		List<Integer> fromOn = BloodTypes.O_NEG.getOrdinalsReceiveFrom();

		return repository.findDonatorsForEachBloodType(minWeight, minAge, 
				maxAge, fromAp, fromAn, fromBp, fromBn, fromABp, fromABn, fromOp, fromOn);
	}


}
