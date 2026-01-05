package com.meditrack.repository;

import com.meditrack.entity.LabResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabResultRepository extends MongoRepository<LabResult, String> {
    List<LabResult> findByPatientId(Long patientId);
    List<LabResult> findByPatientIdOrderByTestDateDesc(Long patientId);
}
