package com.setu.service;

import com.setu.model.Prescription;
import com.setu.repository.PrescriptionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PrescriptionServiceTest {

    private PrescriptionRepository repository;
    private PrescriptionService service;

    @BeforeEach
    void setUp() {
        repository = mock(PrescriptionRepository.class);
        service = new PrescriptionService(repository);
    }

    @Test
    void shouldCreatePrescription() {
        Prescription p = new Prescription("Ibuprofen", "2 per day");

        when(repository.save(p)).thenReturn(p);

        Prescription result = service.createPrescription(p);

        assertNotNull(result);
        assertEquals("Ibuprofen", result.getMedication());
    }

    @Test
    void shouldThrowException_whenMedicationMissing() {
        Prescription p = new Prescription(null, "2 per day");

        assertThrows(IllegalArgumentException.class, () -> {
            service.createPrescription(p);
        });
    }

    @Test
    void shouldReturnMedicationList() {
        List<Prescription> list = List.of(
                new Prescription("Ibuprofen", "2 per day")
        );

        when(repository.findByPatientId(1L)).thenReturn(list);

        List<Prescription> result = service.getPatientMedications(1L);

        assertEquals(1, result.size());
    }
    @Test
    void shouldReturnEmptyList_whenNoMedications() {

        when(repository.findByPatientId(1L))
                .thenReturn(List.of());

        List<Prescription> result = service.getPatientMedications(1L);

        assertTrue(result.isEmpty());
    }
    @Test
    void shouldCallRepository_whenGettingMedications() {

        service.getPatientMedications(1L);

        verify(repository, times(1)).findByPatientId(1L);
    }
}