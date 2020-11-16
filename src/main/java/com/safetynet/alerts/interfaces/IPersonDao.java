package com.safetynet.alerts.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

public interface IPersonDao {
  
  public PersonList getPersonListDao() throws JsonParseException, JsonMappingException, IOException;

  public Person postNewPersonDao();

  public Person updateAPersonDao();

  public Person deleteAPersonDao();

  public PersonList personsCoveredByAFirestationDao(int pFireStationNumber);

  public PersonList childListForAnAdressDao(String pAdress);

  public ArrayList<String> phoneListOfResidentForAGivenFireStationDao(
      int pFireStationNumber);

  public PersonList personListWithCompleteInfoCoveredByFireStationDao(
      String pAdress);

  public PersonList floodPersonListCompleteDao(
      ArrayList<Integer> pListFireStationNumber);

  public Person detailledPersonInfoDao(String pfirstName, String plastName);

  public ArrayList<String> getAllEmailForACityDao(String pCity);
  
}
