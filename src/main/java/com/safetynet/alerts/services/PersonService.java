package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedical;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.utils.WorkingFileOuput;

/**
 * The Class PersonService.
 */
@Service
public class PersonService {
  
  /** The person dao. */
  @Autowired
  private IPersonDao personDao;

  
  /**
   * Gets the persons service.
   *
   * @param filePathData the file path data
   * @return the persons service
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws NoSuchFileException the no such file exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PersonList getpersonsService(String filePathData) throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDao.getPersonListDao(filePathData);
  }
  
  /**
   * Post new person service.
   *
   * @return the person
   */
  public Person postNewPersonService() {
    
    return personDao.postNewPersonDao();
  }
  
  /**
   * Update A person service.
   *
   * @return the person
   */
  public Person updateAPersonService() {
    
    return personDao.updateAPersonDao();
  }
  
  /**
   * Delete A person service.
   *
   * @return the person
   */
  public Person deleteAPersonService() {
   
    return personDao.deleteAPersonDao();
  }
  
  /**
   * Persons covered by A firestation service.
   *
   * @param fireStationNumber the fire station number
   * @return the array list
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ArrayList<PersonAndMedical> personsCoveredByAFirestationService(ArrayList<Integer> fireStationNumber) throws JsonParseException, JsonMappingException, IOException {
    // TODO Auto-generated method stub
    return personDao.personsCoveredByAFirestationDao(fireStationNumber);
  }
  
  /**
   * Child list for an adress service.
   *
   * @param pAdress the adress
   * @return the person list
   */
  public PersonList childListForAnAdressService(String pAdress) {
    // TODO Auto-generated method stub
    return personDao.childListForAnAdressDao(pAdress);
  }
  
  /**
   * Phone list of resident for A given fire station service.
   *
   * @param pFireStationNumber the fire station number
   * @return the array list
   */
  public ArrayList<String> phoneListOfResidentForAGivenFireStationService(
      int pFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.phoneListOfResidentForAGivenFireStationDao(pFireStationNumber);
  }
  
  /**
   * Person list with complete info covered by fire station service.
   *
   * @param pAdress the adress
   * @return the person list
   */
  public PersonList personListWithCompleteInfoCoveredByFireStationService(
      String pAdress) {
    // TODO Auto-generated method stub
    return personDao.personListWithCompleteInfoCoveredByFireStationDao(pAdress);
  }
  
  /**
   * Flood person list complete service.
   *
   * @param pListFireStationNumber the list fire station number
   * @return the person list
   */
  public PersonList floodPersonListCompleteService(
      ArrayList<Integer> pListFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.floodPersonListCompleteDao(pListFireStationNumber);
  }
  
  /**
   * Detailled person info service.
   *
   * @param pfirstName the pfirst name
   * @param plastName the plast name
   * @return the array list
   */
  public ArrayList<PersonAndMedical> detailledPersonInfoService(String pfirstName, String plastName) {
    // TODO Auto-generated method stub
    return personDao.detailledPersonInfoDao(pfirstName, plastName);
  }
  
  /**
   * Gets the all email for A city service.
   *
   * @param pCity the city
   * @return the all email for A city service
   */
  public HashSet<String> getAllEmailForACityService(String pCity) {
    
    
    return personDao.getAllEmailForACityDao(pCity);
  }



  

 

}
