package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingFireStation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * The Interface IFireStationDao.
 */
public interface WorkingFirestationsService {

  ArrayList<WorkingFireStation> reestablishCase(Collection<WorkingFireStation> workingFireStations);

  /**
   * Retrieve Original FiresStation for processing.
   * Return HashMpa with key as address, and value is WorkingFirestation
   *
   * @return HashMap<String, WorkingFireStation>
   */
  public HashMap<Integer, WorkingFireStation> createWorkingFiresStationHashMap();

  HashMap<Integer, WorkingFireStation> getWorkingFireStationHashMap();
}
