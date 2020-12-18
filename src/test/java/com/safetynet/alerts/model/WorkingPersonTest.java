package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {WorkingPerson.class})
@ExtendWith(SpringExtension.class)
public class WorkingPersonTest {
  @Autowired
  private WorkingPerson workingPerson;

  @Test
  public void testSetId() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingPerson.setId(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingPerson.getId());
  }

  @Test
  public void testSetFirstName() {
    this.workingPerson.setFirstName("Jane");
    assertEquals("Jane", this.workingPerson.getFirstName());
  }

  @Test
  public void testSetLastName() {
    this.workingPerson.setLastName("Doe");
    assertEquals("Doe", this.workingPerson.getLastName());
  }

  @Test
  public void testSetHomeID() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingPerson.setHomeID(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingPerson.getHomeID());
  }

  @Test
  public void testSetPhone() {
    this.workingPerson.setPhone("4105551212");
    assertEquals("4105551212", this.workingPerson.getPhone());
  }

  @Test
  public void testSetBirthdate() {
    LocalDate date = LocalDate.ofEpochDay(1L);
    this.workingPerson.setBirthdate(date);
    assertEquals(date,
            this.workingPerson.getBirthdate());
  }

  @Test
  public void testSetAge() {
    this.workingPerson.setAge(1);
    assertEquals(1, this.workingPerson.getAge());
  }

  @Test
  public void testSetEmail() {
    this.workingPerson.setEmail("jane.doe@example.org");
    assertEquals("jane.doe@example.org", this.workingPerson.getEmail());
  }

  @Test
  public void testSetIdMedicalRecord() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingPerson.setIdMedicalRecord(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingPerson.getIdMedicalRecord());
  }



  @Test
  public void testEquals2() {
    WorkingPerson workingPerson = new WorkingPerson();
    workingPerson.setLastName("Doe");
    assertFalse(this.workingPerson.equals(workingPerson));
  }

  @Test
  public void testEquals3() {
    WorkingPerson workingPerson = new WorkingPerson();
    workingPerson.setEmail("jane.doe@example.org");
    assertFalse(this.workingPerson.equals(workingPerson));
  }

  @Test
  public void testEquals4() {
    WorkingPerson workingPerson = new WorkingPerson();
    workingPerson.setPhone("4105551212");
    assertFalse(this.workingPerson.equals(workingPerson));
  }

  @Test
  public void testEquals5() {
    WorkingPerson workingPerson = new WorkingPerson();
    workingPerson.setFirstName("Jane");
    assertFalse(this.workingPerson.equals(workingPerson));
  }

  @Test
  public void testEquals6() {
    LocalDate birthdate = LocalDate.ofEpochDay(1L);
    WorkingPerson workingPerson = new WorkingPerson();
    workingPerson.setBirthdate(birthdate);
    assertFalse(this.workingPerson.equals(workingPerson));
  }




}

