package com.meditrack.controller;

import com.meditrack.entity.LabResult;
import com.meditrack.entity.Patient;
import com.meditrack.repository.LabResultRepository;
import com.meditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab-results")
@CrossOrigin(origins = "*")
public class LabResultController {
    
    @Autowired
    private LabResultRepository labResultRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping
    public ResponseEntity<List<LabResult>> getLabResults(Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        List<LabResult> results = labResultRepository.findByPatientIdOrderByTestDateDesc(patient.getId());
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<LabResult> getLabResult(@PathVariable String id,
                                                   Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        LabResult result = labResultRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Lab result not found"));
        
        if (!result.getPatientId().equals(patient.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        return ResponseEntity.ok(result);
    }
}
