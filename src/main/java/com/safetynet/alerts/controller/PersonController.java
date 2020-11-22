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
import com.safetynet.alerts.model.PersonMedicalFireStationWrapper;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.services.CreateWorkingFileService;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.utils.WorkingFileOuput;


/**
 * The Class PersonController.
 */
@RestController
public class PersonController {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");
    
    /** The person service. */
    @Autowired
    PersonService personService;
 
    
 
    /**
     * Gets the person info controller.
     *
     * @return the person info controller
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
  
  /**
   * Post new person controller.
   *
   * @return the person
   */
  @PostMapping("/person")
  public Person postNewPersonController() {
    return personService.postNewPersonService();
  }
  
  /**
   * Update A person controller.
   *
   * @return the person
   */
  @PutMapping("/person")
  public Person updateAPersonController() {
    return personService.updateAPersonService();
  }
  
  /**
   * Delete A person controller.
   *
   * @return the person
   */
  @DeleteMapping("/person")
  public Person deleteAPersonController() {
    return personService.deleteAPersonService();
  }

  /**
   * Child list for an adress controller.
   *
   * @param pAdress the adress
   * @return the person list
   */
  @GetMapping("/childAlert?adress={pvAdress}")
  public PersonList childListForAnAdressController(@PathVariable("pvAdress") String pAdress) {
    return personService.childListForAnAdressService(pAdress);
  }
  
  /**
   * Phone list of resident for A given fire station controller.
   *
   * @param pFireStationNumber the fire station number
   * @return the array list
   */
  @GetMapping("/phoneAlert?firestation={pvFireStationNumber}")
  public ArrayList<String> phoneListOfResidentForAGivenFireStationController(@PathVariable("pvFireStationNumber") int pFireStationNumber) {
    return personService.phoneListOfResidentForAGivenFireStationService(pFireStationNumber);
  }
  
  /**
   * Person list with complete info covered by fire station controller.
   *
   * @param pAdress the adress
   * @return the person list
   */
  @GetMapping("/fire")
  public ArrayList<PersonMedicalFireStationWrapper> personListWithCompleteInfoCoveredByFireStationController(@RequestParam String adress) {
    return personService.personListWithCompleteInfoCoveredByFireStationService(adress);
  }

 
  /**
   * Detailled person info controller.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @return the array list
   */
  @GetMapping("/personInfo")
  public ArrayList<PersonMedicalFireStationWrapper> detailledPersonInfoController( @RequestParam String firstName, @RequestParam String lastName ) {
    return personService.detailledPersonInfoService(firstName, lastName) ;
  }
  
  /**
   * Gets the all email for A city controller.
   *
   * @param city the city
   * @return the all email for A city controller
   */
  @GetMapping("/communityEmail")
  public HashSet<String> getAllEmailForACityController( @RequestParam String city ) {
    return personService.getAllEmailForACityService(city) ;
  }
  

}
