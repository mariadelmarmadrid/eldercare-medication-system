package com.setu.repository;

import com.setu.model.IntakeRecord;

public interface IntakeRepository {

    IntakeRecord save(IntakeRecord intakeRecord);
}