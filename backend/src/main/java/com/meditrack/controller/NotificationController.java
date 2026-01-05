package com.meditrack.controller;

import com.meditrack.entity.Notification;
import com.meditrack.entity.Patient;
import com.meditrack.repository.NotificationRepository;
import com.meditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        List<Notification> notifications = notificationRepository
            .findByPatientIdOrderByCreatedAtDesc(patient.getId());
        return ResponseEntity.ok(notifications);
    }
    
    @GetMapping("/unread")
    public ResponseEntity<List<Notification>> getUnreadNotifications(Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        List<Notification> notifications = notificationRepository
            .findByPatientIdAndReadFalse(patient.getId());
        return ResponseEntity.ok(notifications);
    }
    
    @PutMapping("/{id}/read")
    public ResponseEntity<Notification> markAsRead(@PathVariable String id,
                                                    Authentication authentication) {
        Patient patient = patientRepository.findByEmail(authentication.getName())
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notification not found"));
        
        if (!notification.getPatientId().equals(patient.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        notification.setRead(true);
        Notification updated = notificationRepository.save(notification);
        return ResponseEntity.ok(updated);
    }
}
