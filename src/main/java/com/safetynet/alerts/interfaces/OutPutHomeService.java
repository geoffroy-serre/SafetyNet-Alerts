package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingHome;
import java.util.UUID;

public interface OutPutHomeService {
  WorkingHome getHomeById(UUID homeId);
}
