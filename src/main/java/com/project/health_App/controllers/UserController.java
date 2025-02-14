package com.project.health_App.controllers;

import com.project.health_App.entities.Patient;
import com.project.health_App.entities.User;
import com.project.health_App.service.PatientService;
import com.project.health_App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    
    @GetMapping
    public String showIndexPage() {
        return "index"; 
    }

    @GetMapping("/map-login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, Model model) {
        boolean isValid = userService.login(email, password);
        if (isValid) {
        	return "dashboard";

        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login"; 
        }
    }
    
    

    @GetMapping("/map-signUp")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        userService.registerUser(user);
        model.addAttribute("message", "Registration successful! Please log in.");
        return "redirect:/map-login";

    }
    
    

}
