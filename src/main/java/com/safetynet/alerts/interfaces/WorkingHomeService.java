package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;

public interface WorkingHomeService {
  WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes);

  ArrayList<WorkingHome> createWorkingHomes(OriginalResponse originalResponse);
}
