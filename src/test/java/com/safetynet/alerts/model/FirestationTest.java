package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FirestationTest {
  
  @Mock
  private Home home = new Home();

  @Test
  public void What_you_set_is_what_you_get() {
    Firestation firestation = new Firestation();
    firestation.setId(1);
    firestation.setStationNumber(3);
    firestation.setHome(home);
    

    assertEquals(firestation.getId(), 1);
    assertEquals(firestation.getStationNumber(), 3);
    assertEquals(firestation.getHome(), home);
    

  }
}
