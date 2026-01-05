package com.meditrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    
    @NotBlank(message = "Department is required")
    private String department;
    
    @NotNull(message = "Appointment date and time is required")
    private LocalDateTime appointmentDateTime;
    
    private String reason;
}
