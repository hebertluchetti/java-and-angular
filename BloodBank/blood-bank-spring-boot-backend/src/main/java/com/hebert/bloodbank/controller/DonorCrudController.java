package com.hebert.bloodbank.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hebert.bloodbank.exception.DuplicateKeyException;
import com.hebert.bloodbank.exception.NullValueException;
import com.hebert.bloodbank.model.Donor;
import com.hebert.bloodbank.service.DonorService;
import com.hebert.bloodbank.service.DonorServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/donors")
@Api("Webservice used for the donor crud operations")
public class DonorCrudController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DonorCrudController.class);
    
    private DonorService service;
    
    DonorCrudController(DonorServiceImpl service) {
    	this.service = service;  
    }
    
    @ApiOperation(value = "Return all donors items")      
    @ApiResponses(value = { 
    		@ApiResponse(code = 200, message = "Query data success"),
    		@ApiResponse(code = 203, message = "The resource was created successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "An unknown exception was thrown")})    
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<List<Donor>> findAll() {
    	LOGGER.info("List All");
    	return ResponseEntity.ok().body(service.findAll()); 		
    }
    
//    @ApiOperation(value = "Find a donor by his id")   
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donor> findById(@PathVariable(value = "id") Long id) {
    	LOGGER.info("Find: id={}", id);  	
		return service.findById(id)
				.map(entity -> ResponseEntity.ok().body(entity))
				.orElse(ResponseEntity.notFound().build());
    }

//    @ApiOperation(value = "Create a donor")   
//    @RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donor> create(@RequestBody Donor video)  {    
    	LOGGER.info("Create: {}", video);  	
    	try {
	        Donor savedDonor = service.save(video);
	        
	        URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest().path("/{id}")
	            .buildAndExpand(savedDonor.getId()).toUri();	
	        LOGGER.info("Created the Donor: {}", video);
	        return ResponseEntity.created(location).body(savedDonor);    
    	} catch (DataIntegrityViolationException e) {
    		throw new DuplicateKeyException("Donor name '" + video.getName() + "' already exists! - " + e.getMessage());	
	    } catch (TransactionSystemException e) {
    		throw new NullValueException("Donor has some value as NULL! - " + e.toString());	
	    }
    }

//    @ApiOperation(value = "Update a donor by his id")   
//    @RequestMapping(value = "/{id}", method =  RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Donor> update(@PathVariable(value = "id") Long id, @RequestBody Donor newDonor) {
    	LOGGER.info("Update: id={}", id);
    	return service.findById(id)
    	           .map(entity -> {
    	               entity.setName(newDonor.getName());  
    	               entity.setCpf(newDonor.getCpf());
    	               entity.setIdentity(newDonor.getIdentity());
    	               entity.setBloodType(newDonor.getBloodType());	   
    	               entity.setGender(newDonor.getGender());
    	               entity.setEmail(newDonor.getEmail());
    	               entity.setHeight(newDonor.getHeight());
    	               entity.setWeight(newDonor.getWeight());
    	               entity.setFather(newDonor.getFather());
    	               entity.setMother(newDonor.getMother());
    	               entity.setBirthDate(newDonor.getBirthDate());
    	               entity.setAddress(newDonor.getAddress());
    	               
    	               Donor updated = service.save(entity);
    	               return ResponseEntity.ok().body(updated);
    	           }).orElse(ResponseEntity.notFound().build());
    }

//    @ApiOperation(value = "Delete a donor by his id")   
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {  
    	LOGGER.info("Delete: id={}", id);  	
    	return service.findById(id)
    	           .map(entity -> {
    	        	   service.delete(entity);
    	               return ResponseEntity.ok().build();
    	           }).orElse(ResponseEntity.notFound().build());
    }
}