package com.safetynet.alerts.model;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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

  @Test
  void testEquals() {
    String result = "WorkingMedicalRecord{idMedicalRecord=00000000-0000-0000-0000-000000000000, " +
            "medications=null, allergies=null}";
    workingMedicalRecord.setIdMedicalRecord(new UUID(0L, 0L));
    assertEquals(result, this.workingMedicalRecord.toString());

  }


}

