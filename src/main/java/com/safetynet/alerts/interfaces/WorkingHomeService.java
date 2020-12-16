package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.WorkingHome;
import java.util.*;

public interface WorkingHomeService {

  /**
   * From lower case to upper case for each first letter
   * @param workingHomes
   * @return ArrayList of WorkingHomes
   */
  ArrayList<WorkingHome> reestablishCase(Collection<WorkingHome> workingHomes);

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

  /**
   * Get data form createWorkingHomes
   * @return ArrayList of WorkingHomes
   */
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

  /**
   *
   * @param homeId
   * @return WorkingHome
   */
  WorkingHome getHomeById(UUID homeId);

  /**
   *
   * @param filePath
   * @return ArrayList of WorkingHomes
   */
  ArrayList<WorkingHome> retrieveWorkingHomeFromFile(String filePath);
}
