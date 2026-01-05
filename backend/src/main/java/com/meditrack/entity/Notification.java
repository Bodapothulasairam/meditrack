package com.meditrack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private String id;
    
    private Long patientId;
    
    private String title;
    
    private String message;
    
    private String type; // APPOINTMENT, LAB_RESULT, GENERAL
    
    private boolean read = false;
    
    @CreatedDate
    private LocalDateTime createdAt;
}
