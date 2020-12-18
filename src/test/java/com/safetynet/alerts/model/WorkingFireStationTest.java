package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WorkingFireStation.class})
@ExtendWith(SpringExtension.class)
public class WorkingFireStationTest {
  @Autowired
  private WorkingFireStation workingFireStation;

  @Test
  public void testAddWorkingHome() {
    // TODO: This test is incomplete.
    //   Reason: No meaningful assertions found.
    //   To help Diffblue Cover to find assertions, please add getters to the
    //   class under test that return fields written by the method under test.
    //   See https://diff.blue/R004

    this.workingFireStation.addWorkingHome(UUID.randomUUID());
  }

  @Test
  public void testSetIdFireStation() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingFireStation.setIdFireStation(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingFireStation.getIdFireStation());
  }

  @Test
  public void testSetStationNumber() {
    this.workingFireStation.setStationNumber(10);
    assertEquals(10, this.workingFireStation.getStationNumber());
  }



}

