package com.meditrack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "lab_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabResult {
    @Id
    private String id;
    
    private Long patientId;
    
    private String testName;
    
    private String testType;
    
    private LocalDateTime testDate;
    
    private Map<String, Object> results;
    
    private String status; // PENDING, COMPLETED, ABNORMAL
    
    private String doctorNotes;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
