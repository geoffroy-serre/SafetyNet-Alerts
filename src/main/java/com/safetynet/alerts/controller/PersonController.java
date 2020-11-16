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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
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
    @GetMapping("/personInfo")
   public PersonList getPersonInfoController(){
      
        try {
          return personService.getpersonsService();
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
  
  @GetMapping("/personInfo?firstName={pvfirstName}&lastName={pvlastName}")
  public Person detailledPersonInfoController( @PathVariable("pvfirstName") String pfirstName, @PathVariable("pvlastName") String plastName ) {
    return personService.detailledPersonInfoService(pfirstName, plastName) ;
  }
  
  @GetMapping("/communityEmail.city={pvCity}")
  public ArrayList<String> getAllEmailForACityController( @PathVariable("pvCity") String pCity ) {
    return personService.getAllEmailForACityService(pCity) ;
  }
  

}
