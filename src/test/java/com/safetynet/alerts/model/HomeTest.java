package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class HomeTest {

  @Test
  public void What_you_set_is_what_you_get() {
    Home home = new Home();
    UUID id = UUID.randomUUID();
    home.setId(id);
    
    home.setAdress("85 rue Jean Jaurès");
    home.setCity("Rochefort");
    home.setZip("17300");
    home.setIdFirestation(id);

    assertEquals(home.getId(), id);
   
    assertEquals(home.getAdress(), "85 rue Jean Jaurès");
    assertEquals(home.getCity(), "Rochefort");
   
    assertEquals(home.getZip(), "17300");
    assertEquals(home.getIdFirestation(), 1);
    assertEquals(home.toString(), "Person [id="+id+", Adress=85 rue Jean Jaurès, "
        + "City=Rochefort,  Zip=17300, FirestationId=1]");

  }
}
