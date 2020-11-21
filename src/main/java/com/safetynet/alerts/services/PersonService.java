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

@Service
public class PersonService {
  
  @Autowired
  private IPersonDao personDao;

  
  public PersonList getpersonsService(String filePathData) throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDao.getPersonListDao(filePathData);
  }
  public Person postNewPersonService() {
    
    return personDao.postNewPersonDao();
  }
  public Person updateAPersonService() {
    
    return personDao.updateAPersonDao();
  }
  public Person deleteAPersonService() {
   
    return personDao.deleteAPersonDao();
  }
  public PersonList personsCoveredByAFirestationService(ArrayList<Integer> fireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.personsCoveredByAFirestationDao(fireStationNumber);
  }
  public PersonList childListForAnAdressService(String pAdress) {
    // TODO Auto-generated method stub
    return personDao.childListForAnAdressDao(pAdress);
  }
  public ArrayList<String> phoneListOfResidentForAGivenFireStationService(
      int pFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.phoneListOfResidentForAGivenFireStationDao(pFireStationNumber);
  }
  public PersonList personListWithCompleteInfoCoveredByFireStationService(
      String pAdress) {
    // TODO Auto-generated method stub
    return personDao.personListWithCompleteInfoCoveredByFireStationDao(pAdress);
  }
  public PersonList floodPersonListCompleteService(
      ArrayList<Integer> pListFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.floodPersonListCompleteDao(pListFireStationNumber);
  }
  public ArrayList<PersonAndMedical> detailledPersonInfoService(String pfirstName, String plastName) {
    // TODO Auto-generated method stub
    return personDao.detailledPersonInfoDao(pfirstName, plastName);
  }
  public HashSet<String> getAllEmailForACityService(String pCity) {
    
    
    return personDao.getAllEmailForACityDao(pCity);
  }



  

 

}
