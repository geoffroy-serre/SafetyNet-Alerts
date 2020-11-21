package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.model.PersonAndMedical;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.services.FireStationService;
import com.safetynet.alerts.services.HomeService;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.utils.WorkingFileOuput;

@RestController
public class FireStationController {

  private static final Logger logger = LogManager.getLogger("App");
    @Autowired
    FireStationService fireStationService;
    @Autowired
    PersonService personService;
    
    /**
     * Get Person Info from personService.
     * @param firstName
     * @param lastName
     * @return ArrayList of Person
     */
   
    
    @GetMapping("/fireStationInfo")
    public FireStationList getFireStationInfoController(){
      
        try {
          return fireStationService.getFireStationListService(WorkingFileOuput.getWorkingInputFile());
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

    
    @PostMapping("/firestation")
    public FireStation postFireStationAdressMappingController() {
      return fireStationService.postFireStationAdressMappingService();
      
    }
    
    @PutMapping("/firestation")
    public FireStation updateFireStationNumberController() {
      return fireStationService.updateFireStationNumberService();
    }
    
    @DeleteMapping("/firestation")
    public FireStation deleteFireStationAdressMappingController() {
      return fireStationService.deleteFireStationAdressMappingService();
    }
  
    @GetMapping("/flood/stations")
    public ArrayList<PersonAndMedical> personsCoveredByAFirestationController( @RequestParam ArrayList<Integer> stations) throws JsonParseException, JsonMappingException, IOException {
      return personService.personsCoveredByAFirestationService(stations) ;
    }
    
   
  
    
}
