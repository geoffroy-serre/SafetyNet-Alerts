package com.safetynet.alerts.model;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {OutPutFireStation.class})
@ExtendWith(SpringExtension.class)
public class OutPutFireStationTest {
  @Autowired
  private OutPutFireStation outPutFireStation;

  @Test
  public void testSetIdFirestation() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.outPutFireStation.setIdFirestation(randomUUIDResult);
    assertSame(randomUUIDResult, this.outPutFireStation.getIdFirestation());
  }

  @Test
  public void testSetStationNumber() {
    this.outPutFireStation.setStationNumber(10);
    assertEquals(10, this.outPutFireStation.getStationNumber());
  }

  @Test
  public void testEquals() {
    assertFalse(this.outPutFireStation.equals("o"));
    assertFalse(this.outPutFireStation.equals(null));
  }


}

