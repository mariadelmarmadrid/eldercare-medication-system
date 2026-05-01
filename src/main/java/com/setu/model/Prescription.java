package com.setu.model;

public class Prescription {

    private String medication;
    private String dosage;

    public Prescription(String medication, String dosage) {
        this.medication = medication;
        this.dosage = dosage;
    }

    public String getMedication() {
        return medication;
    }

    public String getDosage() {
        return dosage;
    }
}