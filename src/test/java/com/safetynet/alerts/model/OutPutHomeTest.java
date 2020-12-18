package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OutPutHome.class})
@ExtendWith(SpringExtension.class)
public class OutPutHomeTest {
  @Autowired
  private OutPutHome outPutHome;

  @Test
  public void testSetIdHome() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.outPutHome.setIdHome(randomUUIDResult);
    assertSame(randomUUIDResult, this.outPutHome.getIdHome());
  }

  @Test
  public void testSetNumberOfAdults() {
    this.outPutHome.setNumberOfAdults(10);
    assertEquals(10, this.outPutHome.getNumberOfAdults().intValue());
  }

  @Test
  public void testSetNumberOfChildren() {
    this.outPutHome.setNumberOfChildren(10);
    assertEquals(10, this.outPutHome.getNumberOfChildren().intValue());
  }

  @Test
  public void testSetStationNumber() {
    this.outPutHome.setStationNumber(10);
    assertEquals(10, this.outPutHome.getStationNumber().intValue());
  }

  @Test
  public void testSetAddress() {
    this.outPutHome.setAddress("42 Main St");
    assertEquals("42 Main St", this.outPutHome.getAddress());
  }

  @Test
  public void testSetCity() {
    this.outPutHome.setCity("Oxford");
    assertEquals("Oxford", this.outPutHome.getCity());
  }

  @Test
  public void testSetZip() {
    this.outPutHome.setZip("21654");
    assertEquals("21654", this.outPutHome.getZip());
  }

  @Test
  public void testEquals() {
    assertFalse(this.outPutHome.equals("o"));
    assertFalse(this.outPutHome.equals(null));
  }

  @Test
  public void testHashCode() {
    assertEquals(29791, this.outPutHome.hashCode());
  }


}

