package com.hebert.bloodbank.service;

import java.util.List;

import com.hebert.bloodbank.model.dto.DonatorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByStateDTO;

public interface DonatorDashboardService {
	 public List<DonatorQuantityByStateDTO> findDonatorQuantityByState();
	 public List<DonatorQuantityByBloodTypeDTO> findDonatorQuantityByBloodType();
	 public List<DonatorAgeAvgByBloodTypeDTO> findDonatorAgeAvgByBloodType();
	 public List<DonatorImcAvgByAgeRangeDTO> findDonatorImcAvgByAgeRange();
	 public List<DonatorImcPercentageByGenderDTO> findDonatorObesityPercentageByGender();
	 public List<DonatorForBloodTypeDTO> findDonatorsForEachBloodType() ;
}

