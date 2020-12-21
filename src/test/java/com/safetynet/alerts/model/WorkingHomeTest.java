package com.safetynet.alerts.model;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {WorkingHome.class})
@ExtendWith(SpringExtension.class)
public class WorkingHomeTest {
  @Autowired
  private WorkingHome workingHome;

  @Test
  public void testSetIdHome() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.workingHome.setIdHome(randomUUIDResult);
    assertSame(randomUUIDResult, this.workingHome.getIdHome());
  }

  @Test
  public void testSetAddress() {
    this.workingHome.setAddress("42 Main St");
    assertEquals("42 Main St", this.workingHome.getAddress());
  }

  @Test
  public void testSetCity() {
    this.workingHome.setCity("Oxford");
    assertEquals("Oxford", this.workingHome.getCity());
  }

  @Test
  public void testSetZip() {
    this.workingHome.setZip("21654");
    assertEquals("21654", this.workingHome.getZip());
  }


  @Test
  public void testEquals2() {
    WorkingHome workingHome = new WorkingHome();
    workingHome.setZip("21654");
    assertFalse(this.workingHome.equals(workingHome));
  }

  @Test
  public void testEquals3() {
    WorkingHome workingHome = new WorkingHome();
    workingHome.setCity("Oxford");
    assertFalse(this.workingHome.equals(workingHome));
  }

  @Test
  public void testEquals4() {
    WorkingHome workingHome = new WorkingHome();
    workingHome.setAddress("42 Main St");
    assertFalse(this.workingHome.equals(workingHome));
  }

  @Test
  public void testHashCode() {
    assertEquals(29791, this.workingHome.hashCode());
  }


}

