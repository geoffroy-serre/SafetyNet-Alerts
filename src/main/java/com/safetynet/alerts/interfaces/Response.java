package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecords;
import com.safetynet.alerts.model.OriginalPersons;
import java.util.ArrayList;

public interface Response {


  void setPersons(ArrayList<?> persons) ;

  ArrayList<?> getPersons();

  void setFirestations(ArrayList<?> firestations) ;


  ArrayList<?> getFirestations();

  void setMedicalrecords(ArrayList<?> medicalrecords) ;


  ArrayList<?> getMedicalrecords();


  @Override
  String toString();
}
