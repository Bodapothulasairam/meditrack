package com.meditrack.service;

import com.meditrack.entity.Patient;
import com.meditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
    }
}
