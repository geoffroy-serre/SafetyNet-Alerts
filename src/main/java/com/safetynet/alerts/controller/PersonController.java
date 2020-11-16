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
import com.safetynet.alerts.services.PersonService;

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
    public PersonList getPersonInfo(){
      
        try {
          return personService.getpersons();
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
  public Person postNewPerson() {
    return personService.postNewPerson();
  }
  
  @PutMapping("/person")
  public Person updateAPerson() {
    return personService.updateAPerson();
  }
  
  @DeleteMapping("/person")
  public Person deleteAPerson() {
    return personService.deleteAPerson();
  }

  @GetMapping("/childAlert?adress={pvAdress}")
  public PersonList childListForAnAdress(@PathVariable("pvAdress") String pAdress) {
    return personService.childListForAnAdress(pAdress);
  }
  
  @GetMapping("/phoneAlert?firestation={pvFireStationNumber}")
  public ArrayList<String> phoneListOfResidentForAGivenFireStation(@PathVariable("pvFireStationNumber") String pFireStationNumber) {
    return personService.phoneListOfResidentForAGivenFireStation(pFireStationNumber);
  }
  
  @GetMapping("/fire?adress={pvAdress}")
  public PersonList personListWithCompleteInfoCoveredByFireStation(@PathVariable("pvAdress") String pAdress) {
    return personService.personListWithCompleteInfoCoveredByFireStation(pAdress);
  }

  @GetMapping("/flood/stations?stations={pvListOfStationNumber}")
  public PersonList floodPersonListComplete( @PathVariable("pvListOfStationNumber") ArrayList<Integer> pListFireStationNumber) {
    return personService.floodPersonListComplete(pListFireStationNumber) ;
  }
  
  @GetMapping("/personInfo?firstName={pvfirstName}&lastName={pvlastName}")
  public Person detailledPersonInfo( @PathVariable("pvfirstName") String pfirstName, @PathVariable("pvlastName") String plastName ) {
    return personService.detailledPersonInfo(pfirstName, plastName) ;
  }
  
  @GetMapping("/communityEmail.city={pvCity}")
  public ArrayList<String> getAllEmailForACity( @PathVariable("pvCity") String pCity ) {
    return personService.getAllEmailForACity(pCity) ;
  }
  

}
