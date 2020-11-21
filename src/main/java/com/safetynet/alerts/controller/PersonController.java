package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedical;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.services.CreateWorkingFileService;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.utils.WorkingFileOuput;

@RestController
public class PersonController {

  private static final Logger logger = LogManager.getLogger("App");
    @Autowired
    PersonService personService;
 
    
 /**
  * Get all persons Info    
  * @return PersonList
  */
    @GetMapping("/allPersonInfo")
   public PersonList getPersonInfoController(){
      
        try {
          return personService.getpersonsService(WorkingFileOuput.getWorkingInputFile());
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
  
  @PostMapping("/person")
  public Person postNewPersonController() {
    return personService.postNewPersonService();
  }
  
  @PutMapping("/person")
  public Person updateAPersonController() {
    return personService.updateAPersonService();
  }
  
  @DeleteMapping("/person")
  public Person deleteAPersonController() {
    return personService.deleteAPersonService();
  }

  @GetMapping("/childAlert?adress={pvAdress}")
  public PersonList childListForAnAdressController(@PathVariable("pvAdress") String pAdress) {
    return personService.childListForAnAdressService(pAdress);
  }
  
  @GetMapping("/phoneAlert?firestation={pvFireStationNumber}")
  public ArrayList<String> phoneListOfResidentForAGivenFireStationController(@PathVariable("pvFireStationNumber") int pFireStationNumber) {
    return personService.phoneListOfResidentForAGivenFireStationService(pFireStationNumber);
  }
  
  @GetMapping("/fire?adress={pvAdress}")
  public PersonList personListWithCompleteInfoCoveredByFireStationController(@PathVariable("pvAdress") String pAdress) {
    return personService.personListWithCompleteInfoCoveredByFireStationService(pAdress);
  }

  @GetMapping("/flood/stations?stations={pvListOfStationNumber}")
  public PersonList floodPersonListCompleteController( @PathVariable("pvListOfStationNumber") ArrayList<Integer> pListFireStationNumber) {
    return personService.floodPersonListCompleteService(pListFireStationNumber) ;
  }
  
  @GetMapping("/personInfo")
  public ArrayList<PersonAndMedical> detailledPersonInfoController( @RequestParam String firstName, @RequestParam String lastName ) {
    return personService.detailledPersonInfoService(firstName, lastName) ;
  }
  
  @GetMapping("/communityEmail")
  public HashSet<String> getAllEmailForACityController( @RequestParam String city ) {
    return personService.getAllEmailForACityService(city) ;
  }
  

}
