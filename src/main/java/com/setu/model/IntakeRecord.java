package com.setu.model;

public class IntakeRecord {

    private Long patientId;
    private Long prescriptionId;
    private String scheduledTime;

    public IntakeRecord(Long patientId, Long prescriptionId, String scheduledTime) {
        this.patientId = patientId;
        this.prescriptionId = prescriptionId;
        this.scheduledTime = scheduledTime;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }
}