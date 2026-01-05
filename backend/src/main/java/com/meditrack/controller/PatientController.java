package com.meditrack.controller;

import com.meditrack.entity.Patient;
import com.meditrack.repository.PatientRepository;
import com.meditrack.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping("/me")
    public ResponseEntity<Patient> getCurrentPatient(Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        return ResponseEntity.ok(patient);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    @PutMapping("/me")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patientDetails, 
                                                 Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setPhoneNumber(patientDetails.getPhoneNumber());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setAddress(patientDetails.getAddress());
        patient.setEmergencyContact(patientDetails.getEmergencyContact());
        
        Patient updatedPatient = patientRepository.save(patient);
        return ResponseEntity.ok(updatedPatient);
    }
}
