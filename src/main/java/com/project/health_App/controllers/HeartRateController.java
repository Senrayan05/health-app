package com.project.health_App.controllers;

import com.project.health_App.entities.HeartRate;
import com.project.health_App.entities.Patient;
import com.project.health_App.repositories.HeartRateRepository;
import com.project.health_App.repositories.PatientRepository;
import com.project.health_App.service.HeartRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/heart-rate")
public class HeartRateController {
	
//testing
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private HeartRateRepository heartRateRepository;

	
    private final HeartRateService heartRateService;

    public HeartRateController(HeartRateService heartRateService) {
        this.heartRateService = heartRateService;
    }

    @GetMapping("/patient/{patientId}")
    public String getHeartRateByPatient(@PathVariable("patientId") Long patientId, Model model) {
        System.out.println("Received request for patientId: " + patientId);

        List<HeartRate> heartRates = heartRateService.getHeartRatesByPatientId(patientId);

        if (heartRates == null || heartRates.isEmpty()) {
            model.addAttribute("error", "No heart rate data found for this patient.");
        } else {
            model.addAttribute("heartRates", heartRates);
        }

        model.addAttribute("patientId", patientId); // Pass the patient ID
        return "heart-rate-details"; 
    }


    
    @GetMapping("/heart-rate/patient/{id}")
    public String getHeartRates(@PathVariable Long id, Model model) {
        List<HeartRate> heartRates = heartRateService.getHeartRatesByPatientId(id);
        model.addAttribute("heartRates", heartRates);
        return "heart-rate-details"; 
    }



    @GetMapping("/details/{id}")
    public String viewPatient(@PathVariable("id") Long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        model.addAttribute("patient", patient);
        return "patient-details";
    }

    @GetMapping("/list")
    public String listHeartRates(Model model) {
        List<HeartRate> heartRates = heartRateService.getAllHeartRates();
        model.addAttribute("heartRates", heartRates);
        return "heart-rate-list";  
    }
    
  
    
    @PostMapping("/update")
    public String updateHeartRate(@RequestParam Long id, @RequestParam int heartRate) {
        heartRateService.updateHeartRate(id, heartRate);
        return "redirect:/heart-rate/list"; 
    }
    
    @PostMapping("/add")
    public String addHeartRate(@RequestParam("patientId") Long patientId, 
                               @RequestParam("heartRate") int heartRate, 
                               RedirectAttributes redirectAttributes) {
        System.out.println("Adding heart rate for Patient ID: " + patientId + " with value: " + heartRate);
        
        Optional<Patient> patientOpt = patientRepository.findById(patientId);
        if (patientOpt.isPresent()) {
            HeartRate newHeartRate = new HeartRate(patientOpt.get(), heartRate);
            heartRateRepository.save(newHeartRate);
            redirectAttributes.addFlashAttribute("success", "Heart rate added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Patient not found!");
        }
        return "redirect:/heart-rate/patient/" + patientId;
    }


    

}
