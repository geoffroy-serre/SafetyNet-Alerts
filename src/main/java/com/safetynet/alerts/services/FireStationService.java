package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;

@Component
public class FireStationService {
  
  @Autowired
  private IFireStationDao fireStationDao;


  
  public FireStationList getFireStationListService() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return fireStationDao.getFireStationListDao();
  }



  
  public FireStation postFireStationAdressMappingService() {
    return fireStationDao.postFireStationMappingDao();
    
  }
  
  public FireStation updateFireStationNumberService() {
    return fireStationDao.updateFireStationNumberDao();
    
  }
  
  public FireStation deleteFireStationAdressMappingService() {
    return fireStationDao.deleteFireStationMappingDao();
    
  }

 

}
