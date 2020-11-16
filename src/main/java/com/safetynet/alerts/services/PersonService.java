package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

@Component
public class PersonService {
  
  @Autowired
  private IPersonDao personDao;


  
  public PersonList getpersons() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDao.getPersonListDto();
  }



  public Person postNewPerson() {
    
    return personDao.postNewPerson();
  }



  public Person updateAPerson() {
    
    return personDao.updateAPerson();
  }


  public Person deleteAPerson() {
   
    return personDao.deleteAPerson();
  }



  
  public PersonList personsCoveredByAFirestation(int pFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.personsCoveredByAFirestation(pFireStationNumber);
  }



  public PersonList childListForAnAdress(String pAdress) {
    // TODO Auto-generated method stub
    return personDao.childListForAnAdress(pAdress);
  }



  public ArrayList<String> phoneListOfResidentForAGivenFireStation(
      String pFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.phoneListOfResidentForAGivenFireStation(pFireStationNumber);
  }



  public PersonList personListWithCompleteInfoCoveredByFireStation(
      String pAdress) {
    // TODO Auto-generated method stub
    return personDao.personListWithCompleteInfoCoveredByFireStation(pAdress);
  }



  public PersonList floodPersonListComplete(
      ArrayList<Integer> pListFireStationNumber) {
    // TODO Auto-generated method stub
    return personDao.floodPersonListComplete(pListFireStationNumber);
  }



  public Person detailledPersonInfo(String pfirstName, String plastName) {
    // TODO Auto-generated method stub
    return personDao.detailledPersonInfo(pfirstName, plastName);
  }



  public ArrayList<String> getAllEmailForACity(String pCity) {
    // TODO Auto-generated method stub
    return personDao.getAllEmailForACity(pCity);
  }

 

}
