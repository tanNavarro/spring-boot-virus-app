package com.cnav.springboot.rest.proj.virusrecords.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnav.springboot.rest.proj.virusrecords.model.InfectedPerson;

@Repository
public interface InfectedPersonRepository extends JpaRepository<InfectedPerson, Long> {
	Page<InfectedPerson> findByVirusId(Long virusId, Pageable pageable);
    Optional<InfectedPerson> findByIdAndVirusId(Long id, Long virusId);

}
