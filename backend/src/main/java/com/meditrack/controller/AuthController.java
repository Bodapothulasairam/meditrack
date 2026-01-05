package com.meditrack.controller;

import com.meditrack.dto.AuthRequest;
import com.meditrack.dto.AuthResponse;
import com.meditrack.entity.Patient;
import com.meditrack.repository.PatientRepository;
import com.meditrack.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Patient patient) {
        if (patientRepository.existsByEmail(patient.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already taken!");
        }
        
        if (patientRepository.existsByPhoneNumber(patient.getPhoneNumber())) {
            return ResponseEntity.badRequest().body("Error: Phone number is already taken!");
        }
        
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        Patient savedPatient = patientRepository.save(patient);
        
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(patient.getEmail(), patient.getPassword())
        );
        
        String jwt = tokenProvider.generateToken(authentication);
        
        return ResponseEntity.ok(new AuthResponse(
            jwt,
            "Bearer",
            savedPatient.getId(),
            savedPatient.getEmail(),
            savedPatient.getFirstName(),
            savedPatient.getLastName()
        ));
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        Patient patient = patientRepository.findByEmail(authRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        return ResponseEntity.ok(new AuthResponse(
            jwt,
            "Bearer",
            patient.getId(),
            patient.getEmail(),
            patient.getFirstName(),
            patient.getLastName()
        ));
    }
}
