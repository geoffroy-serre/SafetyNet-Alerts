package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FireStationTest {
  
  @Mock
  private Home home = new Home();

  @Test
  public void What_you_set_is_what_you_get() {
    FireStation firestation = new FireStation();
    UUID id = UUID.randomUUID();
    firestation.setId(id);
    firestation.setStation(3);
    firestation.setHome(id);
    

    assertEquals(firestation.getId(), id);
    assertEquals(firestation.getStation(), 3);
    assertEquals(firestation.getHome(), id);
    

  }
}
