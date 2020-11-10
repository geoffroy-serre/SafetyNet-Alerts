package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HomeTest {

  @Test
  public void What_you_set_is_what_you_get() {
    Home home = new Home();
    home.setId(1);
    
    home.setAdress("85 rue Jean Jaurès");
    home.setCity("Rochefort");
    home.setZip("17300");
    home.setIdFirestation(1);

    assertEquals(home.getId(), 1);
   
    assertEquals(home.getAdress(), "85 rue Jean Jaurès");
    assertEquals(home.getCity(), "Rochefort");
   
    assertEquals(home.getZip(), "17300");
    assertEquals(home.getIdFirestation(), 1);

  }
}
