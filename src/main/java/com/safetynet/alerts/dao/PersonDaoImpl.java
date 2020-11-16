package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.IPersonDao;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.utils.OriginalInputFile;

@Component
public class PersonDaoImpl implements IPersonDao {
  private static final Logger logger = LogManager.getLogger("App");

  
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public PersonList getPersonListDao() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    DateFormat df = new SimpleDateFormat("MM-dd-YYYY");
    objectMapper.setDateFormat(df);

    PersonList personsList = objectMapper.readValue(new File(OriginalInputFile.getOriginalInputFile()),
        PersonList.class);
    
    logger.info("PersonList retrieval");

    return personsList;
  }


  @Override
  public Person postNewPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Person updateAPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Person deleteAPersonDao() {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public PersonList personsCoveredByAFirestationDao(int pFireStationNumber) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public PersonList childListForAnAdressDao(String pAdress) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public ArrayList<String> phoneListOfResidentForAGivenFireStationDao(
      int pFireStationNumber) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public PersonList personListWithCompleteInfoCoveredByFireStationDao(
      String pAdress) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public PersonList floodPersonListCompleteDao(
      ArrayList<Integer> pListFireStationNumber) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public Person detailledPersonInfoDao(String pfirstName, String plastName) {
    // TODO Auto-generated method stub
    return null;
  }


  @Override
  public ArrayList<String> getAllEmailForACityDao(String pCity) {
    // TODO Auto-generated method stub
    return null;
  }


  
  

}
