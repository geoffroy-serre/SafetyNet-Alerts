package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingFireStation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public interface WorkingFirestationsService {

  /**
   * From lower case to each first letter in uppercase
   * @param workingFireStations
   * @return ArrayList of WorkingFireStation
   */
  ArrayList<WorkingFireStation> reestablishCase(Collection<WorkingFireStation> workingFireStations);

  /**
   * Retrieve Original FiresStation for processing.
   * Return HashMpa with key as address, and value is WorkingFirestation
   *
   * @return HashMap<String, WorkingFireStation>
   */
  public HashMap<Integer, WorkingFireStation> createWorkingFiresStationHashMap();

  /**
   * Key Station number, value WorkingFirestation
   * @return hashMap of Integer and WorkingFireStation
   */
  HashMap<Integer, WorkingFireStation> getWorkingFireStationHashMap();
}
