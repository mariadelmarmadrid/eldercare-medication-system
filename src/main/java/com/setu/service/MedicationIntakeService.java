package com.setu.service;

import com.setu.model.IntakeRecord;
import com.setu.repository.IntakeRepository;

public class MedicationIntakeService {

    private IntakeRepository intakeRepository;
    private IntakeValidationService validationService;

    public MedicationIntakeService(
            IntakeRepository intakeRepository,
            IntakeValidationService validationService) {
        this.intakeRepository = intakeRepository;
        this.validationService = validationService;
    }

    public IntakeRecord confirmMedicationIntake(IntakeRecord intakeRecord) {

        validationService.validateDuplicateConfirmation(intakeRecord);
        validationService.validateScheduleWindow(intakeRecord);

        return intakeRepository.save(intakeRecord);
    }
}