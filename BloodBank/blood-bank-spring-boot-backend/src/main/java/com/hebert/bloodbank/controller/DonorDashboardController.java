package com.hebert.bloodbank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hebert.bloodbank.model.dto.DonorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorQuantityByStateDTO;
import com.hebert.bloodbank.service.DonorDashboardService;
import com.hebert.bloodbank.service.DonorDashboardServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/donors")
@Api("Webservice used for the donor dashboard operations")
public class DonorDashboardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DonorDashboardController.class);
    
    private DonorDashboardService donorDashboardService;
    
    DonorDashboardController(DonorDashboardServiceImpl donorDashboardService) {  
    	this.donorDashboardService = donorDashboardService;
    }
    
    @ApiOperation(value = "Find the quantity of donors grouped by his state")   
    @RequestMapping(value = "/quant-by-state", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorQuantityByStateDTO>> findDonorQuantityByState() {
    	LOGGER.info("Find the quantity of donors grouped by his state");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorQuantityByState()); 		
    }
       
    @ApiOperation(value = "Find the age average of donors grouped by his blood type")  
    @RequestMapping(value = "/age-avg-by-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorAgeAvgByBloodTypeDTO>> findDonorAgeAvgByBloodType() {
    	LOGGER.info("Find the age average of donors grouped by his blood type");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorAgeAvgByBloodType()); 		
    }
    
    @ApiOperation(value = "Find the imc average of donors grouped by his age range")  
    @RequestMapping(value = "/imc-avg-by-age-range", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorImcAvgByAgeRangeDTO>> findDonorImcAvgByAgeRange() {
    	LOGGER.info("Find the imc average of donors grouped by his age range");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorImcAvgByAgeRange()); 		
    }
    
    @ApiOperation(value = "Find the obesity percentage for donors grouped by gender")  
    @RequestMapping(value = "/obesity-by-gender", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorImcPercentageByGenderDTO>> findDonorObesityPercentageByGender() {
    	LOGGER.info("Find the obesity percentage for donors grouped by gender");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorObesityPercentageByGender()); 		
    }
    
    @ApiOperation(value = "Find the quantity of possible donors for each blood type")
    @RequestMapping(value = "/quant-for-each-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorForBloodTypeDTO>> findDonorsForEachBloodType() {
    	LOGGER.info("Find the quantity of possible donors for each blood type");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorsForEachBloodType()); 		
    }
    
    @ApiOperation(value = "Find the quantity of donors grouped by his blood type")
    @RequestMapping(value = "/quant-by-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonorQuantityByBloodTypeDTO>> findDonorQuantityByBloodType() {
    	LOGGER.info("Find the quantity of donors grouped by his blood type");
    	return ResponseEntity.ok().body(donorDashboardService.findDonorQuantityByBloodType()); 		
    }
    
    
    
}