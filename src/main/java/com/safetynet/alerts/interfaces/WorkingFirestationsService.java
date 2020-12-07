package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingFireStation;
import java.util.HashMap;

/**
 * The Interface IFireStationDao.
 */
public interface WorkingFirestationsService {

  public HashMap<String, WorkingFireStation> createWorkingFiresStationHashMap(String filePath);
}
