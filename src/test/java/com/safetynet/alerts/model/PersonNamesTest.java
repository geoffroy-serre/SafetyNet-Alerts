package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PersonNames.class})
@ExtendWith(SpringExtension.class)
public class PersonNamesTest {
  @Autowired
  private PersonNames personNames;

  @Test
  public void testSetFirstName() {
    this.personNames.setFirstName("Jane");
    assertEquals("Jane", this.personNames.getFirstName());
  }

  @Test
  public void testSetLastName() {
    this.personNames.setLastName("Doe");
    assertEquals("Doe", this.personNames.getLastName());
  }


}

