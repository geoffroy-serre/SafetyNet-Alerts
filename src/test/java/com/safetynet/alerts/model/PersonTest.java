package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PersonTest {

  @Test
  public void What_you_set_is_what_you_get(){
    Person person = new Person();
    person.setId(1);
    person.setLastName("Serre");
    person.setFirstName("Geoffroy");
    person.setCellNumber("06.19.67.49.45");
    person.setBirthDate("14/04/1982");
    person.setEmail("geff1982@gmail.com");
    person.setIdHome(123);
    person.setIdMedicalRecord(456);
    
    assertEquals(person.getId(), 1);
    assertEquals(person.getFirstName(), "Geoffroy");
    assertEquals(person.getLastName(), "Serre");
    assertEquals(person.getCellNumber(), "06.19.67.49.45");
    assertEquals(person.getBirthDate(), "14/04/1982");
    assertEquals(person.getEmail(), "geff1982@gmail.com");
    assertEquals(person.getIdHome(), 123);
    assertEquals(person.getIdMedicalRecord(), 456);
    
  }
}
