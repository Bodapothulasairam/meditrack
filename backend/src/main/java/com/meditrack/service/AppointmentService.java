package com.meditrack.service;

import com.meditrack.dto.AppointmentRequest;
import com.meditrack.entity.Appointment;
import com.meditrack.entity.Patient;
import com.meditrack.repository.AppointmentRepository;
import com.meditrack.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    public Appointment bookAppointment(Long patientId, AppointmentRequest request) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctorName(request.getDoctorName());
        appointment.setDepartment(request.getDepartment());
        appointment.setAppointmentDateTime(request.getAppointmentDateTime());
        appointment.setReason(request.getReason());
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);
        
        return appointmentRepository.save(appointment);
    }
}
