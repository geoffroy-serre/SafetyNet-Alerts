package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OriginalFirestation.class})
@ExtendWith(SpringExtension.class)
public class OriginalFirestationTest {
  @Autowired
  private OriginalFirestation originalFirestation;

  @Test
  public void testSetAddress() {
    this.originalFirestation.setAddress("42 Main St");
    assertEquals("42 Main St", this.originalFirestation.getAddress());
  }

  @Test
  public void testSetStation() {
    this.originalFirestation.setStation(1);
    assertEquals(1, this.originalFirestation.getStation().intValue());
  }



  @Test
  public void testEquals() {
    assertFalse(this.originalFirestation.equals("o"));
    assertFalse(this.originalFirestation.equals(null));
  }

}

