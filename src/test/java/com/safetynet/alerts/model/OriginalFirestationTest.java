package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OriginalFirestation.class})
@ExtendWith(SpringExtension.class)
public class OriginalFirestationTest {
  @Autowired
  private OriginalFirestation originalFirestation;

  @Test
  public void testSetAddress() {
    String address = "42 Main St";
    this.originalFirestation.setAddress(address);
    assertEquals("42 Main St", this.originalFirestation.getAddress());
  }

  @Test
  public void testSetStation() {
    String station = "Station";
    this.originalFirestation.setStation(station);
    assertEquals("Station", this.originalFirestation.getStation());
  }

  @Test
  public void testToString() {
    String actualToStringResult = this.originalFirestation.toString();
    assertEquals("FirestationsItem{address = '42 Main St',station = 'Station'}",
            actualToStringResult);
  }
}

