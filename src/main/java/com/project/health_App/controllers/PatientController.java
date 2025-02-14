package com.project.health_App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.health_App.entities.Patient;
import com.project.health_App.repositories.PatientRepository;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/adding-patient")
    public String showAddPatientPage() {
        return "add-patient"; 
    }
    
    

    @PostMapping("/patient-added")
    public String addPatient(@ModelAttribute Patient patient, Model model) {
        patientRepository.save(patient);
        return "redirect:/patient-list"; 
    }


    @GetMapping("/patient-list")
    public String getAllPatients(Model model) {
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients", patients); 
        return "patient-details"; 
    }


    //testing
    
    @GetMapping("/details/{id}")
    public String viewPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        model.addAttribute("patient", patient);
        return "patient-details";
    }

    
    
    


}
