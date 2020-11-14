package com.safetynet.alerts.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class MedicalRecordTest {

  
  @Test
  public void What_you_set_is_what_you_get() {
    MedicalRecord medicalRecord = new MedicalRecord();
    
    medicalRecord.setFirstName("Geff");
    medicalRecord.setLastName("Serre");
    UUID id = medicalRecord.getId();
    
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
    assertEquals(medicalRecord.getLastName(), "Serre");
    assertEquals(medicalRecord.getFirstName(), "Geff");
    assertEquals(medicalRecord.toString(), "MedicalRecord [id="+id+",firstName=Geff, "
        + "lastName=Serre allergies=[Peanuts, Dumb People], medications=[Seresta, Doliprane]]");
    
    

  }
}
