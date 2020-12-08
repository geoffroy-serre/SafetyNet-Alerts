package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public interface WorkingHomeService {
  /**
   * Return Object Working Home found by equality between
   * address parameter and given list occurrence.
   *
   * @param address      String
   * @param workingHomes ArrayList<WorkingHome>
   * @return WorkingHome
   */
  WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes);
  /**
   * Get workingHomes from OriginalPersonObjet in original file.
   * OriginalFile input can't be set with the constant FilesPath.
   * Unique adresses set by address, city, zip.
   *
   * @return HashSet<WorkingHome> WorkingHome.
   */
  HashSet<WorkingHome> createWorkingHomes();

  ArrayList<WorkingHome> getWorkingHomesArrayList();
  /**
   * Use for processing, it has ID in key.
   *
   * @return HashMap<UUID, WorkingHome>
   */
  HashMap<UUID, WorkingHome> getFinishedWorkingHomesHashMap();
  /**
   * use for linking home  to others object.
   * Key "address,city,zip"
   *
   * @return
   */
  public HashMap<String, WorkingHome> getUnFinishedWorkingHomesHashMap();

  WorkingHome getHomeById(UUID homeId);
}
