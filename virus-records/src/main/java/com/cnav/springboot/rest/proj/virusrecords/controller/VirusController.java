package com.cnav.springboot.rest.proj.virusrecords.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnav.springboot.rest.proj.virusrecords.exception.ResourceNotFoundException;
import com.cnav.springboot.rest.proj.virusrecords.model.Virus;
import com.cnav.springboot.rest.proj.virusrecords.repository.VirusRepository;

@RestController
@RequestMapping("/api")
public class VirusController {
	
	@Autowired
    VirusRepository virusRepository;
	
    // Get All Virus
	@GetMapping("/virus")
	public List<Virus> getAllVirus() {
	    return virusRepository.findAll();
	}

    // Create a new Virus
	@PostMapping("/virus")
	public Virus createVirus(@Valid @RequestBody Virus virus) {
	    return virusRepository.save(virus);
	}

    // Get a Single Virus
	@GetMapping("/virus/{id}")
	public Virus getVirusById(@PathVariable(value = "id") Long virusId) {
	    return virusRepository.findById(virusId)
	            .orElseThrow(() -> new ResourceNotFoundException("Virus", "id", virusId));
	}

    // Update a Virus
	@PutMapping("/virus/{id}")
	public Virus updateVirus(@PathVariable(value = "id") Long virusId,
	                                        @Valid @RequestBody Virus virusDetails) {

		Virus virus = virusRepository.findById(virusId)
	            .orElseThrow(() -> new ResourceNotFoundException("Virus", "id", virusId));

		virus.setName(virusDetails.getName());
		virus.setDesc(virusDetails.getDesc());

	    Virus updatedVirus = virusRepository.save(virus);
	    return updatedVirus;
	}

    // Delete a Virus
	
	@DeleteMapping("/virus/{id}")
	public ResponseEntity<?> deleteVirus(@PathVariable(value = "id") Long virusId) {
		Virus virus = virusRepository.findById(virusId)
	            .orElseThrow(() -> new ResourceNotFoundException("Virus", "id", virusId));

		virusRepository.delete(virus);
	    return ResponseEntity.ok().build();
	}

}
