package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DeleteFirestationTest {
  @Test
  public void testSetAddress() {
    DeleteFirestation deleteFirestation = new DeleteFirestation();
    deleteFirestation.setAddress("42 Main St");
    assertEquals("42 Main St", deleteFirestation.getAddress());
  }

  @Test
  public void testSetStation() {
    DeleteFirestation deleteFirestation = new DeleteFirestation();
    deleteFirestation.setStation(1);
    assertEquals(1, deleteFirestation.getStation().intValue());
  }


  @Test
  public void testEquals() {
    assertFalse((new DeleteFirestation()).equals("o"));
    assertFalse((new DeleteFirestation()).equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(961, (new DeleteFirestation()).hashCode());
  }
}

