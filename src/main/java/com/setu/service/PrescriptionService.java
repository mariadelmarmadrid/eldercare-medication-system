package com.setu.service;

import com.setu.model.Prescription;
import com.setu.repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService {

    private PrescriptionRepository repository;

    public PrescriptionService(PrescriptionRepository repository) {
        this.repository = repository;
    }

    public Prescription createPrescription(Prescription prescription) {
        if (prescription.getMedication() == null) {
            throw new IllegalArgumentException("Medication required");
        }
        return repository.save(prescription);
    }

    public List<Prescription> getPatientMedications(Long patientId) {
        return repository.findByPatientId(patientId);
    }
}