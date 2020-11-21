package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;

/**
 * The Class FireStationService.
 */
@Service
public class FireStationService {
  
  /** The fire station dao. */
  @Autowired
  private IFireStationDao fireStationDao;


  
  /**
   * Gets the fire station list service.
   *
   * @param filePath the file path
   * @return the fire station list service
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws NoSuchFileException the no such file exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public FireStationList getFireStationListService(String filePath) throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return fireStationDao.getFireStationListDao(filePath);
  }



  
  /**
   * Post fire station adress mapping service.
   *
   * @return the fire station
   */
  public FireStation postFireStationAdressMappingService() {
    return fireStationDao.postFireStationMappingDao();
    
  }
  
  /**
   * Update fire station number service.
   *
   * @return the fire station
   */
  public FireStation updateFireStationNumberService() {
    return fireStationDao.updateFireStationNumberDao();
    
  }
  
  /**
   * Delete fire station adress mapping service.
   *
   * @return the fire station
   */
  public FireStation deleteFireStationAdressMappingService() {
    return fireStationDao.deleteFireStationMappingDao();
    
  }

 

}
