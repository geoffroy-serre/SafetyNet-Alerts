package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalFirestation;
import java.util.ArrayList;

public interface OriginalFireStationService {
  /**
   * Get fireStation from original data file.
   * @return ArrayList<OriginalFirestation>
   */
  ArrayList<OriginalFirestation> getOriginalFireStations();
}
