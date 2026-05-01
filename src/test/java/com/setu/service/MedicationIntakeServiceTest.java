package com.setu.service;

import com.setu.model.IntakeRecord;
import com.setu.repository.IntakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicationIntakeServiceTest {

    private IntakeRepository intakeRepository;
    private IntakeValidationService validationService;
    private MedicationIntakeService medicationIntakeService;

    @BeforeEach
    void setUp() {
        intakeRepository = mock(IntakeRepository.class);
        validationService = mock(IntakeValidationService.class);

        medicationIntakeService =
                new MedicationIntakeService(intakeRepository, validationService);
    }

    @Test
    void shouldConfirmMedicationIntakeSuccessfully() {
        IntakeRecord intake =
                new IntakeRecord(1L, 101L, "2025-05-20T08:00");

        when(intakeRepository.save(intake)).thenReturn(intake);

        IntakeRecord result =
                medicationIntakeService.confirmMedicationIntake(intake);

        assertNotNull(result);

        verify(validationService).validateDuplicateConfirmation(intake);
        verify(validationService).validateScheduleWindow(intake);
        verify(intakeRepository).save(intake);
    }

    @Test
    void shouldThrowExceptionWhenDuplicateIntakeDetected() {
        IntakeRecord intake =
                new IntakeRecord(1L, 101L, "2025-05-20T08:00");

        doThrow(new IllegalArgumentException("Duplicate intake"))
                .when(validationService)
                .validateDuplicateConfirmation(intake);

        assertThrows(
                IllegalArgumentException.class,
                () -> medicationIntakeService.confirmMedicationIntake(intake)
        );

        verify(intakeRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenOutsideScheduleWindow() {
        IntakeRecord intake =
                new IntakeRecord(1L, 101L, "2025-05-20T08:00");

        doThrow(new IllegalArgumentException("Outside schedule"))
                .when(validationService)
                .validateScheduleWindow(intake);

        assertThrows(
                IllegalArgumentException.class,
                () -> medicationIntakeService.confirmMedicationIntake(intake)
        );

        verify(intakeRepository, never()).save(any());
    }
}