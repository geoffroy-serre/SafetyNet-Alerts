package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingFireStation;
import java.util.HashMap;

/**
 * The Interface IFireStationDao.
 */
public interface WorkingFirestationsService {

  /**
   * Retrieve Original FiresStation for processing.
   * Return HashMpa with key as address, and value is WorkingFirestation
   *
   * @return HashMap<String, WorkingFireStation>
   */
  public HashMap<String, WorkingFireStation> createWorkingFiresStationHashMap();
}
