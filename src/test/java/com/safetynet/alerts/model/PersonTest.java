package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class PersonTest {

  @Test
  public void What_you_set_is_what_you_get(){
    Person person = new Person();
    LocalDate  birthday = LocalDate.of(1982, 04, 14);
    UUID id = UUID.randomUUID();
    person.setId(id);
    person.setLastName("Serre");
    person.setFirstName("Geoffroy");
    person.setCellNumber("+33619674945");   
    person.setBirthDate(birthday);
    person.setEmail("geff1982@gmail.com");
    person.setIdHome(id);
    person.setIdMedicalRecord(id);
    person.setAge(38);
    
    assertEquals(person.getId(), id);
    assertEquals(person.getFirstName(), "Geoffroy");
    assertEquals(person.getLastName(), "Serre");
    assertEquals(person.getCellNumber(), "+33619674945");
    assertEquals(person.getBirthDate(), birthday);
    assertEquals(person.getEmail(), "geff1982@gmail.com");
    assertEquals(person.getIdHome(), id);
    assertEquals(person.getIdMedicalRecord(), id);
    assertEquals(person.getAge(), 38);
    assertEquals (person.toString(), "Person [id="+id+", firstName=Geoffroy, lastName=Serre,"
        + " email=geff1982@gmail.com, idMedicalRecord="+id+", idHome="+id+", birthdate=1982-04-14]");
    
  }
}
