package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.*;
import java.util.*;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.BeanUtils;
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

  @Override
  public ArrayList<WorkingHome> reestablishCase(Collection<WorkingHome> workingHomes){
    ArrayList<WorkingHome> result = new ArrayList<>();
    for (WorkingHome workingHome : workingHomes){
      WorkingHome processingHome = new WorkingHome();
      BeanUtils.copyProperties(workingHome, processingHome);
      processingHome.setAddress(WordUtils.capitalize(workingHome.getAddress()));
      processingHome.setCity(WordUtils.capitalize(workingHome.getCity()));
      processingHome.setZip(WordUtils.capitalize(workingHome.getZip()));
      result.add(processingHome);
    }
    return result;
  }

  @Override
  public WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes) {
    for (WorkingHome workingHome : workingHomes) {
      if (workingHome.getAddress().equals(address)) {
        return workingHome;
      }
    }
    return null;

  }

  @Override
  public ArrayList<WorkingHome> getWorkingHomesArrayList() {
    ArrayList<WorkingHome> workingHomes = new ArrayList<WorkingHome>();
    workingHomes.addAll(createWorkingHomes());
    return workingHomes;
  }

  @Override
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

  @Override
  public HashMap<UUID, WorkingHome> getFinishedWorkingHomesHashMap() {

    HashMap<UUID, WorkingHome> hashMapWorkingHomes = new HashMap<>();
    for (WorkingHome workingHome : createWorkingHomes()) {
      hashMapWorkingHomes.put(workingHome.getIdHome(), workingHome);
    }
    return hashMapWorkingHomes;
  }

  @Override
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
