package com.hebert.bloodbank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hebert.bloodbank.model.dto.DonatorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorForBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByStateDTO;
import com.hebert.bloodbank.service.DonatorDashboardService;
import com.hebert.bloodbank.service.DonatorDashboardServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/donators")
@Api("Webservice used for the donator dashboard operations")
public class DonatorDashboardController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DonatorDashboardController.class);
    
    private DonatorDashboardService donatorDashboardService;
    
    DonatorDashboardController(DonatorDashboardServiceImpl donatorDashboardService) {  
    	this.donatorDashboardService = donatorDashboardService;
    }
    
    @ApiOperation(value = "Find the quantity of donators grouped by his state")   
    @RequestMapping(value = "/quant-by-state", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorQuantityByStateDTO>> findDonatorQuantityByState() {
    	LOGGER.info("Find the quantity of donators grouped by his state");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorQuantityByState()); 		
    }
       
    @ApiOperation(value = "Find the age average of donators grouped by his blood type")  
    @RequestMapping(value = "/age-avg-by-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorAgeAvgByBloodTypeDTO>> findDonatorAgeAvgByBloodType() {
    	LOGGER.info("Find the age average of donators grouped by his blood type");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorAgeAvgByBloodType()); 		
    }
    
    @ApiOperation(value = "Find the imc average of donators grouped by his age range")  
    @RequestMapping(value = "/imc-avg-by-age-range", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorImcAvgByAgeRangeDTO>> findDonatorImcAvgByAgeRange() {
    	LOGGER.info("Find the imc average of donators grouped by his age range");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorImcAvgByAgeRange()); 		
    }
    
    @ApiOperation(value = "Find the obesity percentage for donators grouped by gender")  
    @RequestMapping(value = "/obesity-by-gender", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorImcPercentageByGenderDTO>> findDonatorObesityPercentageByGender() {
    	LOGGER.info("Find the obesity percentage for donators grouped by gender");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorObesityPercentageByGender()); 		
    }
    
    @ApiOperation(value = "Find the quantity of possible donators for each blood type")
    @RequestMapping(value = "/quant-for-each-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorForBloodTypeDTO>> findDonatorsForEachBloodType() {
    	LOGGER.info("Find the quantity of possible donators for each blood type");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorsForEachBloodType()); 		
    }
    
    @ApiOperation(value = "Find the quantity of donators grouped by his blood type")
    @RequestMapping(value = "/quant-by-blood-type", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<DonatorQuantityByBloodTypeDTO>> findDonatorQuantityByBloodType() {
    	LOGGER.info("Find the quantity of donators grouped by his blood type");
    	return ResponseEntity.ok().body(donatorDashboardService.findDonatorQuantityByBloodType()); 		
    }
    
    
    
}