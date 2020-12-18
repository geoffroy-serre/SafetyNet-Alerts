package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)

public class OutPutPersonTest {
  @Mock
  private OutPutMedicalRecord outPutMedicalRecord;

  @Mock
  private OutPutHome outPutHome;

@InjectMocks
  private OutPutPerson outPutPerson;

  @Test
  public void testSetIdHome() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.outPutPerson.setIdHome(randomUUIDResult);
    assertSame(randomUUIDResult, this.outPutPerson.getIdHome());
  }

  @Test
  public void testSetIdMedicalRecord() {
    UUID randomUUIDResult = UUID.randomUUID();
    this.outPutPerson.setIdMedicalRecord(randomUUIDResult);
    assertSame(randomUUIDResult, this.outPutPerson.getIdMedicalRecord());
  }

  @Test
  public void testSetFirstName() {
    this.outPutPerson.setFirstName("Jane");
    assertEquals("Jane", this.outPutPerson.getFirstName());
  }

  @Test
  public void testSetLastName() {
    this.outPutPerson.setLastName("Doe");
    assertEquals("Doe", this.outPutPerson.getLastName());
  }

  @Test
  public void testSetHome() {
    this.outPutPerson.setHome(this.outPutHome);
    assertEquals(this.outPutHome.toString(),
            this.outPutPerson.getHome().toString());
  }

  @Test
  public void testSetPhone() {
    this.outPutPerson.setPhone("4105551212");
    assertEquals("4105551212", this.outPutPerson.getPhone());
  }

  @Test
  public void testSetBirthdate() {
    LocalDate date =LocalDate.ofEpochDay(1L);
            this.outPutPerson.setBirthdate(LocalDate.ofEpochDay(1L));
    assertEquals(date,
            this.outPutPerson.getBirthdate());
  }

  @Test
  public void testSetAge() {
    this.outPutPerson.setAge(1);
    assertEquals(1, this.outPutPerson.getAge().intValue());
  }

  @Test
  public void testSetEmail() {
    this.outPutPerson.setEmail("jane.doe@example.org");
    assertEquals("jane.doe@example.org", this.outPutPerson.getEmail());
  }

  @Test
  public void testSetMedicalRecord() {

    this.outPutPerson.setMedicalRecord(this.outPutMedicalRecord);
    assertEquals(this.outPutPerson.toString(),
            this.outPutPerson.toString());
  }

  @Test
  public void testEquals() {
    assertFalse(this.outPutPerson.equals("obj"));
    assertTrue(this.outPutPerson.equals(this.outPutPerson));
    assertTrue(this.outPutPerson.equals(new OutPutPerson()));
  }

  @Test
  public void testEquals2() {
    OutPutPerson outPutPerson = new OutPutPerson();
    outPutPerson.setLastName("Doe");
    assertFalse(this.outPutPerson.equals(outPutPerson));
  }

  @Test
  public void testEquals3() {
    OutPutPerson outPutPerson = new OutPutPerson();
    outPutPerson.setEmail("jane.doe@example.org");
    assertFalse(this.outPutPerson.equals(outPutPerson));
  }

  @Test
  public void testEquals4() {
    OutPutPerson outPutPerson = new OutPutPerson();
    outPutPerson.setPhone("4105551212");
    assertFalse(this.outPutPerson.equals(outPutPerson));
  }

  @Test
  public void testEquals5() {
    OutPutPerson outPutPerson = new OutPutPerson();
    outPutPerson.setFirstName("Jane");
    assertFalse(this.outPutPerson.equals(outPutPerson));
  }

  @Test
  public void testEquals6() {
    LocalDate birthdate = LocalDate.ofEpochDay(1L);
    OutPutPerson outPutPerson = new OutPutPerson();
    outPutPerson.setBirthdate(birthdate);
    assertFalse(this.outPutPerson.equals(outPutPerson));
  }

  @Test
  public void testHashCode() {
    assertEquals(28629151, this.outPutPerson.hashCode());
  }


}

