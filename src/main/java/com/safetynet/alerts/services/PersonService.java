package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

@Service
public class PersonService {
  
  @Autowired
  private IPersonDao personDao;

  
  public PersonList getpersonsService() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDao.getPersonListDao();
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



  
  public PersonList personsCoveredByAFirestationService(int pFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.personsCoveredByAFirestationDao(pFireStationNumber);
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



  public Person detailledPersonInfoService(String pfirstName, String plastName) {
    // TODO Auto-generated method stub
    return personDao.detailledPersonInfoDao(pfirstName, plastName);
  }



  public ArrayList<String> getAllEmailForACityService(String pCity) {
    // TODO Auto-generated method stub
    return personDao.getAllEmailForACityDao(pCity);
  }



  

 

}
