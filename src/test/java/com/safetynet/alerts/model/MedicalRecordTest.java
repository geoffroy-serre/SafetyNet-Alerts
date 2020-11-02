package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class MedicalRecordTest {

  
  @Test
  public void What_you_set_is_what_you_get() {
    MedicalRecord medicalRecord = new MedicalRecord();
    
    medicalRecord.setId(1);
    List<String> allergy = new ArrayList<String>();
    List<String> medication = new ArrayList<String>();
    
    allergy.add("Peanuts");
    allergy.add("Dumb People");
    medication.add("Seresta");
    medication.add("Doliprane");
    
    medicalRecord.setAllergy(allergy);
    medicalRecord.setMedication(medication);
    

    assertEquals(medicalRecord.getId(), 1);
    assertEquals(medicalRecord.getAllergy(), allergy);
    assertEquals(medicalRecord.getMedication(), medication);
    

  }
}
