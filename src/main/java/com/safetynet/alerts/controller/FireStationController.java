package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.HomeService;

@RestController
public class FireStationController {

  private static final Logger logger = LogManager.getLogger("App");
    @Autowired
    FireStationService fireStationService;
    
    /**
     * Get Person Info from personService.
     * @param firstName
     * @param lastName
     * @return ArrayList of Person
     */
   
    
    @GetMapping("/fireStationInfo")
    public FireStationList getFireStationInfo(){
      
        try {
          return fireStationService.getFireStationList();
        } catch (JsonParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("parse");
        } catch (JsonMappingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("mapping");
        } catch (NoSuchFileException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("tfile");
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          logger.info("io du cul");
        }  
      
      return null;
    }
  
  
}
