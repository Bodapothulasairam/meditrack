package com.meditrack.controller;

import com.meditrack.dto.AppointmentRequest;
import com.meditrack.entity.Appointment;
import com.meditrack.entity.Patient;
import com.meditrack.repository.AppointmentRepository;
import com.meditrack.repository.PatientRepository;
import com.meditrack.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointments(Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        List<Appointment> appointments = appointmentRepository.findByPatientId(patient.getId());
        return ResponseEntity.ok(appointments);
    }
    
    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(@Valid @RequestBody AppointmentRequest request,
                                                        Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Appointment appointment = appointmentService.bookAppointment(patient.getId(), request);
        return ResponseEntity.ok(appointment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id, 
                                                      Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        if (!appointment.getPatient().getId().equals(patient.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        return ResponseEntity.ok(appointment);
    }
    
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id,
                                                         Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Appointment appointment = appointmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        if (!appointment.getPatient().getId().equals(patient.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
        Appointment updated = appointmentRepository.save(appointment);
        return ResponseEntity.ok(updated);
    }
}
