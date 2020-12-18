package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WorkingMedicalRecord.class})
@ExtendWith(SpringExtension.class)
public class WorkingMedicalRecordTest {
  @Autowired
  private WorkingMedicalRecord workingMedicalRecord;

  @Test
  public void testSetIdMedicalRecord() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingMedicalRecord.setIdMedicalRecord(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingMedicalRecord.getIdMedicalRecord());
  }



}

