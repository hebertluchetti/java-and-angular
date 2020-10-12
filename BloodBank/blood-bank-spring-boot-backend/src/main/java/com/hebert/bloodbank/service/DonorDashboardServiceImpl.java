package com.hebert.bloodbank.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByStateDTO;
import com.hebert.bloodbank.repository.DonorDashboardRepository;

@Service
public class DonorDashboardServiceImpl implements DonorDashboardService {
	
	private DonorDashboardRepository repository;
	
	public DonorDashboardServiceImpl(DonorDashboardRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<DonorQuantityByStateDTO> findDonorQuantityByState() {
		return repository.findDonorQuantityByState();
	}

	@Override
	public List<DonorAgeAvgByBloodTypeDTO> findDonorAgeAvgByBloodType() {
		return repository.findDonorAgeAvgByBloodType();
	}

	@Override
	public List<DonorImcAvgByAgeRangeDTO> findDonorImcAvgByAgeRange() {
		return repository.findDonorImcAvgByAgeRange();
	}
	
	@Override
	public List<DonorImcPercentageByGenderDTO> findDonorObesityPercentageByGender() {
		BigDecimal imcValue = new BigDecimal(30); // IMC > 30 determines the person is obese
		return repository.findDonorImcPercentageByGender(imcValue);
	}
	
	@Override
	public List<DonorQuantityByBloodTypeDTO> findDonorQuantityByBloodType() {
		return repository.findDonorQuantityByBloodType();
	}

	@Override
	public List<DonorForBloodTypeDTO> findDonorsForEachBloodType() {
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

		return repository.findDonorsForEachBloodType(minWeight, minAge, 
				maxAge, fromAp, fromAn, fromBp, fromBn, fromABp, fromABn, fromOp, fromOn);
	}


}
