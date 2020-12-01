package com.safetynet.alerts.interfaces;


import java.util.ArrayList;

public interface Response {
  void setPersons(ArrayList<?> persons);

  ArrayList<?> getPersons();

  void setFirestations(ArrayList<?> firestations);

  ArrayList<?> getFirestations();

  void setMedicalrecords(ArrayList<?> medicalrecords);

  ArrayList<?> getMedicalrecords();

  @Override
  String toString();
}
