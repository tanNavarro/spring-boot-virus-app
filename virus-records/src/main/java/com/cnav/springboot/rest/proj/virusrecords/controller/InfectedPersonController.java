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
import com.cnav.springboot.rest.proj.virusrecords.model.InfectedPerson;
import com.cnav.springboot.rest.proj.virusrecords.model.Virus;
import com.cnav.springboot.rest.proj.virusrecords.repository.InfectedPersonRepository;
import com.cnav.springboot.rest.proj.virusrecords.repository.VirusRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api")
public class InfectedPersonController {
	
	@Autowired
    InfectedPersonRepository infectedPersonRepository;
	
	@Autowired
    VirusRepository virusRepository;
	
    // Get All Infected Person by Virus ID
	@GetMapping("/virus/{virusId}/infected")
	public Page<InfectedPerson> getAllCommentsByPostId(@PathVariable (value = "virusId") Long virusId,
            Pageable pageable) {
	return infectedPersonRepository.findByVirusId(virusId, pageable);
	}

    // Add Infected Person to Virus ID
	@PostMapping("/virus/{virusId}/infected")
	public InfectedPerson createInfectedPerson(@PathVariable (value = "virusId") Long virusId,
			@Valid @RequestBody InfectedPerson infectedPerson) {
		 return virusRepository.findById(virusId).map(virus -> {
			 	infectedPerson.setVirus(virus);
	            return infectedPersonRepository.save(infectedPerson);
	        }).orElseThrow(() -> new ResourceNotFoundException("Virus", "id", virusId));
	}    
	
	// Update Infected Person Info
    @PutMapping("/virus/{virusId}/infected/{infectedPersonId}")
    public InfectedPerson updateComment(@PathVariable (value = "virusId") Long virusId,
                                 @PathVariable (value = "infectedPersonId") Long infectedPersonId,
                                 @Valid @RequestBody InfectedPerson infectedPersonRequest) {
        if(!virusRepository.existsById(virusId)) {
            throw new ResourceNotFoundException("Virus", "id", virusId);
        }

        return infectedPersonRepository.findById(infectedPersonId).map(infectedPerson -> {
        	infectedPerson.setName(infectedPersonRequest.getName());
        	infectedPerson.setContactNumber(infectedPersonRequest.getContactNumber());
            return infectedPersonRepository.save(infectedPerson);
        }).orElseThrow(() -> new ResourceNotFoundException("InfectedPerson", "id", infectedPersonId));
    }

    // Delete Infected Person 
    @DeleteMapping("/virus/{virusId}/infected/{infectedPersonId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "virusId") Long virusId,
                              @PathVariable (value = "infectedPersonId") Long infectedPersonId) {
        return infectedPersonRepository.findByIdAndVirusId(infectedPersonId, virusId).map(infectedPerson -> {
        	infectedPersonRepository.delete(infectedPerson);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("InfectedPerson", "id", infectedPersonId));
    }
}
