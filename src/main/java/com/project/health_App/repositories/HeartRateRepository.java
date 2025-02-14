package com.project.health_App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.health_App.entities.HeartRate;
import com.project.health_App.entities.Patient;
import java.util.List;

@Repository
public interface HeartRateRepository extends JpaRepository<HeartRate, Long> {
    List<HeartRate> findByPatient(Patient patient);

	List<HeartRate> findByPatientId(Long id); 
}