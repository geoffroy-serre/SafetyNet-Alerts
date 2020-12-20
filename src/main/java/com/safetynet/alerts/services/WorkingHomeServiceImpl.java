package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveWorkingDataRepository;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingResponse;
import java.util.*;
import org.apache.commons.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class HomeService.
 */
@Service
public class WorkingHomeServiceImpl implements WorkingHomeService {

  final Logger logger = LogManager.getLogger("WorkingHomeServiceImpl");
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  @Autowired
  RetrieveWorkingDataRepository retrieveWorkingDataRepository;

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<WorkingHome> reestablishCase(Collection<WorkingHome> workingHomes) {
    logger.debug("Entering reestablishCase ");

    ArrayList<WorkingHome> result = new ArrayList<>();
    for (WorkingHome workingHome : workingHomes) {
      WorkingHome processingHome = new WorkingHome();
      BeanUtils.copyProperties(workingHome, processingHome);
      processingHome.setAddress(WordUtils.capitalize(workingHome.getAddress()));
      processingHome.setCity(WordUtils.capitalize(workingHome.getCity()));
      processingHome.setZip(WordUtils.capitalize(workingHome.getZip()));
      result.add(processingHome);
    }
    logger.debug("Success reestablishCase ");
    return result;
  }

  /**
   * @inheritDoc
   */
  @Override
  public WorkingHome searchWorkingHome(String address, ArrayList<WorkingHome> workingHomes) {
    logger.debug("Entering searchWorkingHome ");
    for (WorkingHome workingHome : workingHomes) {
      if (workingHome.getAddress().equals(address)) {
        logger.debug("Success searchWorkingHome ");
        return workingHome;
      }
    }
    logger.debug("Not match found searchWorkingHome return null");
    return null;

  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<WorkingHome> getWorkingHomesArrayList() {
    ArrayList<WorkingHome> workingHomes = new ArrayList<WorkingHome>();
    workingHomes.addAll(createWorkingHomes());
    return workingHomes;
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashSet<WorkingHome> createWorkingHomes() {
    logger.debug("Entering createWorkingHomes ");
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
    logger.debug("Success createWorkingHomes ");
    return homeHashSet;
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<UUID, WorkingHome> getFinishedWorkingHomesHashMap() {
    logger.debug("Entering getFinishedWorkingHomesHashMap ");
    HashMap<UUID, WorkingHome> hashMapWorkingHomes = new HashMap<>();
    for (WorkingHome workingHome : createWorkingHomes()) {
      hashMapWorkingHomes.put(workingHome.getIdHome(), workingHome);
    }
    logger.debug("Success getFinishedWorkingHomesHashMap ");
    return hashMapWorkingHomes;
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<String, WorkingHome> getUnFinishedWorkingHomesHashMap() {
    logger.debug("Entering getUnFinishedWorkingHomesHashMap ");
    HashMap<String, WorkingHome> hashMapWorkingHomes = new HashMap<>();
    for (WorkingHome workingHome : createWorkingHomes()) {
      hashMapWorkingHomes.put(workingHome.getAddress(), workingHome);
    }
    logger.debug("Success getUnFinishedWorkingHomesHashMap ");
    return hashMapWorkingHomes;
  }

  /**
   * @inheritDoc
   */
  @Override
  public WorkingHome getHomeById(UUID homeId) {
    logger.debug("Entering getHomeById ");
    WorkingHome workingHome ;
    ArrayList<WorkingHome> workingHomes =
            retrieveWorkingDataRepository.getWorkingData(FilesPath.WORKING_INPUT_FILE).getHomes();
    for (WorkingHome currentWorkingHome : workingHomes) {
      if (currentWorkingHome.getIdHome().equals(homeId)) {
        workingHome = currentWorkingHome;
        logger.debug("Success getHomeById ");
        return workingHome;
      }
    }
    logger.debug("Not Match found getHomeById return null ");
    return null;
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<WorkingHome> retrieveWorkingHomeFromFile(String filePath) {
    WorkingResponse wr = retrieveWorkingDataRepository.getWorkingData(filePath);
    ArrayList<WorkingHome> ws = wr.getHomes();
    return ws;
  }

}
