package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public interface WorkingHomeService {
  WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes);

  HashSet<WorkingHome> createWorkingHomes();

  ArrayList<WorkingHome> getWorkingHomesArrayList();

  HashMap<UUID, WorkingHome> getFinishedWorkingHomesHashMap();
  public HashMap<String, WorkingHome> getUnFinishedWorkingHomesHashMap();
}
