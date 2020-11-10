package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MedicalRecordTest {

  
  @Test
  public void What_you_set_is_what_you_get() {
    MedicalRecord medicalRecord = new MedicalRecord();
    
    
    ArrayList<String> allergy = new ArrayList<String>();
    ArrayList<String> medication = new ArrayList<String>();
    
    allergy.add("Peanuts");
    allergy.add("Dumb People");
    medication.add("Seresta");
    medication.add("Doliprane");
    
    medicalRecord.setAllergies(allergy);
    medicalRecord.setMedications(medication);
   
    assertEquals(medicalRecord.getAllergies(), allergy);
    assertEquals(medicalRecord.getMedication(), medication);
    

  }
}
