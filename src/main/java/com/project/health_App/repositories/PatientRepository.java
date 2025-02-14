package com.project.health_App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.health_App.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {}
