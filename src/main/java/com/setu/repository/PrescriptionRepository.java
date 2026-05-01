package com.setu.repository;

import com.setu.model.Prescription;
import java.util.List;

public interface PrescriptionRepository {

    Prescription save(Prescription prescription);

    List<Prescription> findByPatientId(Long patientId);
}