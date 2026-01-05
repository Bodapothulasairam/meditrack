package com.meditrack.repository;

import com.meditrack.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByPatientIdOrderByCreatedAtDesc(Long patientId);
    List<Notification> findByPatientIdAndReadFalse(Long patientId);
    long countByPatientIdAndReadFalse(Long patientId);
}
