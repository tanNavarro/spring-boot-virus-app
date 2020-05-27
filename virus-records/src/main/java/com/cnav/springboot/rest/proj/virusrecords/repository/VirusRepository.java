package com.cnav.springboot.rest.proj.virusrecords.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cnav.springboot.rest.proj.virusrecords.model.Virus;

@Repository
public interface VirusRepository extends JpaRepository<Virus, Long> {

}
