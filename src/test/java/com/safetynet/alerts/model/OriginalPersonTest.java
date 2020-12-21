package com.safetynet.alerts.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {OriginalPerson.class})
@ExtendWith(SpringExtension.class)
public class OriginalPersonTest {
  @Autowired
  private OriginalPerson originalPerson;

  @Test
  public void testSetZip() {
    this.originalPerson.setZip("21654");
    assertEquals("21654", this.originalPerson.getZip());
  }

  @Test
  public void testSetFirstName() {
    this.originalPerson.setFirstName("Jane");
    assertEquals("Jane", this.originalPerson.getFirstName());
  }

  @Test
  public void testSetLastName() {
    this.originalPerson.setLastName("Doe");
    assertEquals("Doe", this.originalPerson.getLastName());
  }

  @Test
  public void testSetAddress() {
    this.originalPerson.setAddress("42 Main St");
    assertEquals("42 Main St", this.originalPerson.getAddress());
  }

  @Test
  public void testSetCity() {
    this.originalPerson.setCity("Oxford");
    assertEquals("Oxford", this.originalPerson.getCity());
  }

  @Test
  public void testSetPhone() {
    this.originalPerson.setPhone("4105551212");
    assertEquals("4105551212", this.originalPerson.getPhone());
  }

  @Test
  public void testSetEmail() {
    this.originalPerson.setEmail("jane.doe@example.org");
    assertEquals("jane.doe@example.org", this.originalPerson.getEmail());
  }


}

