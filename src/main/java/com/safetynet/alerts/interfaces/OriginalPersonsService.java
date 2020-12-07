package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalPersons;
import java.util.HashMap;

public interface OriginalPersonsService {
  HashMap<String, OriginalPersons> getOriginalPersonHashMap();
}
