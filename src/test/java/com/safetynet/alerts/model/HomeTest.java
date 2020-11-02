package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HomeTest {

  @Test
  public void What_you_set_is_what_you_get() {
    Home home = new Home();
    home.setId(1);
    home.setHomeNumber(85);
    home.setStreet("rue Jean Jaurès");
    home.setCity("Rochefort");
    home.setState("Charente-Maritime");
    home.setPostalCode("17300");
    home.setIdFirestation(1);

    assertEquals(home.getId(), 1);
    assertEquals(home.getHomeNumber(), 85);
    assertEquals(home.getStreet(), "rue Jean Jaurès");
    assertEquals(home.getCity(), "Rochefort");
    assertEquals(home.getState(), "Charente-Maritime");
    assertEquals(home.getPostalCode(), "17300");
    assertEquals(home.getIdFirestation(), 1);

  }
}
