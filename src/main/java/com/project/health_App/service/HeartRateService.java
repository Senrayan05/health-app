package com.project.health_App.service;

import com.project.health_App.entities.HeartRate;
import com.project.health_App.entities.Patient;
import com.project.health_App.repositories.HeartRateRepository;
import com.project.health_App.repositories.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HeartRateService {

    private final HeartRateRepository heartRateRepository;
    private final PatientRepository patientRepository;

    public HeartRateService(HeartRateRepository heartRateRepository, PatientRepository patientRepository) {
        this.heartRateRepository = heartRateRepository;
        this.patientRepository = patientRepository;
    }

    public List<HeartRate> getHeartRatesByPatientId(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        return patient.map(heartRateRepository::findByPatient).orElseGet(List::of); 
    }

    public List<HeartRate> getAllHeartRates() {
        return heartRateRepository.findAll();
    }
    
    public void updateHeartRate(Long id, int newHeartRate) {
        Optional<HeartRate> optionalHeartRate = heartRateRepository.findById(id);
        if (optionalHeartRate.isPresent()) {
            HeartRate heartRate = optionalHeartRate.get();
            heartRate.setHeartRate(newHeartRate);
            heartRateRepository.save(heartRate);
        } else {
            throw new RuntimeException("Heart rate record not found");
        }
    }

}
