package com.hebert.bloodbank.service;

import java.util.List;

import com.hebert.bloodbank.model.dto.DonorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByStateDTO;

public interface DonorDashboardService {
	 public List<DonorQuantityByStateDTO> findDonorQuantityByState();
	 public List<DonorQuantityByBloodTypeDTO> findDonorQuantityByBloodType();
	 public List<DonorAgeAvgByBloodTypeDTO> findDonorAgeAvgByBloodType();
	 public List<DonorImcAvgByAgeRangeDTO> findDonorImcAvgByAgeRange();
	 public List<DonorImcPercentageByGenderDTO> findDonorObesityPercentageByGender();
	 public List<DonorForBloodTypeDTO> findDonorsForEachBloodType() ;
}

