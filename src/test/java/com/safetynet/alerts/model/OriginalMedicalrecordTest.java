package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OriginalMedicalrecord.class})
@ExtendWith(SpringExtension.class)
public class OriginalMedicalrecordTest {
  @Autowired
  private OriginalMedicalrecord originalMedicalrecord;

  @Test
  public void testSetFirstName() {
    this.originalMedicalrecord.setFirstName("Jane");
    assertEquals("Jane", this.originalMedicalrecord.getFirstName());
  }

  @Test
  public void testSetLastName() {
    this.originalMedicalrecord.setLastName("Doe");
    assertEquals("Doe", this.originalMedicalrecord.getLastName());
  }

  @Test
  public void testSetBirthdate() {
    LocalDate date = LocalDate.ofEpochDay(1L);
    this.originalMedicalrecord.setBirthdate(date);
    assertEquals(date,
            this.originalMedicalrecord.getBirthdate());
  }


}

