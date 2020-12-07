package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalFirestation;
import java.util.HashMap;

public interface OriginalFireStationService {
  HashMap<Integer, OriginalFirestation> getOriginalFireStationHashMap();
}
