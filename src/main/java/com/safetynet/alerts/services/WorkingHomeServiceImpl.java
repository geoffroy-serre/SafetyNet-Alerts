package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class HomeService.
 */
@Service
public class WorkingHomeServiceImpl implements WorkingHomeService {

  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Autowired
  RetrieveWorkingDataRepository retrieveWorkingDataRepository;


  /**
   * Return Object Working Home found by equality between
   * address parameter and given list occurrence.
   *
   * @param address      String
   * @param workingHomes ArrayList<WorkingHome>
   * @return WorkingHome
   */
  public WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes) {
    for (WorkingHome workingHome : workingHomes) {
      if (workingHome.getAddress().equals(address)) {
        return workingHome;
      }
    }
    return null;

  }

  public ArrayList<WorkingHome> getWorkingHomesArrayList() {
    ArrayList<WorkingHome> workingHomes = new ArrayList<WorkingHome>();
    workingHomes.addAll(createWorkingHomes());
    return workingHomes;
  }

  /**
   * Get workingHomes from OriginalPersonObjet in original file.
   * OriginalFile input can't be set with the constant FilesPath.
   * Unique adresses set by address, city, zip.
   *
   * @return HashSet<WorkingHome> WorkingHome.
   */
  public HashSet<WorkingHome> createWorkingHomes() {
    OriginalResponse originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);

    HashSet<WorkingHome> homeHashSet = new HashSet<WorkingHome>();
    for (OriginalPerson originalPerson : originalResponse.getPersons()) {
      WorkingHome addingHome = new WorkingHome();
      addingHome.setAddress(originalPerson.getAddress());
      addingHome.setCity(originalPerson.getCity());
      addingHome.setZip(originalPerson.getZip());
      addingHome.setIdHome(UUID.randomUUID());
      homeHashSet.add(addingHome);
    }
    return homeHashSet;
  }

  /**
   * Use for processing, it has ID in key.
   *
   * @return HashMap<UUID, WorkingHome>
   */
  public HashMap<UUID, WorkingHome> getFinishedWorkingHomesHashMap() {

    HashMap<UUID, WorkingHome> hashMapWorkingHomes = new HashMap<>();
    for (WorkingHome workingHome : createWorkingHomes()) {
      hashMapWorkingHomes.put(workingHome.getIdHome(), workingHome);
    }
    return hashMapWorkingHomes;
  }

  /**
   * use for linking home  to others object.
   * Key "address,city,zip"
   *
   * @return
   */
  public HashMap<String, WorkingHome> getUnFinishedWorkingHomesHashMap() {

    HashMap<String, WorkingHome> hashMapWorkingHomes = new HashMap<>();
    for (WorkingHome workingHome : createWorkingHomes()) {
      hashMapWorkingHomes.put(workingHome.getAddress() , workingHome);

    }
    return hashMapWorkingHomes;
  }

  @Override
  public WorkingHome getHomeById(UUID homeId) {
    WorkingHome workingHome = new WorkingHome();
    ArrayList<WorkingHome> workingHomes =
            retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getHomes();
    for (WorkingHome currentWorkingHome : workingHomes) {
      if (currentWorkingHome.getIdHome().equals(homeId)) {
        workingHome = currentWorkingHome;
        return workingHome;
      }
    }
    return null;
  }

  @Override
  public  ArrayList<WorkingHome> retrieveWorkingHomeFromFile(String filePath){
    WorkingResponse wr = retrieveWorkingDataRepository.getWorkingData(filePath);
    ArrayList<WorkingHome> ws = wr.getHomes();
    return ws;
  }

}
