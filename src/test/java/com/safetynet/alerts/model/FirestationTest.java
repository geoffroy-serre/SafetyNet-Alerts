package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FirestationTest {
  
  @Mock
  private Home home = new Home();

  @Test
  public void What_you_set_is_what_you_get() {
    Firestation firestation = new Firestation();
    UUID id = UUID.randomUUID();
    firestation.setId(id);
    firestation.setStationNumber(3);
    firestation.setHome(id);
    

    assertEquals(firestation.getId(), id);
    assertEquals(firestation.getStationNumber(), 3);
    assertEquals(firestation.getHome(), id);
    

  }
}
