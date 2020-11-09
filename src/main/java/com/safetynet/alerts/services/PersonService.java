package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.dao.PersonDao;
import com.safetynet.alerts.model.Person;

@Component
public class PersonService {
  
  @Autowired
  private PersonDao personDao;
  
/**
 * Get person info from PersonDao.
 * @param firstName
 * @param lastName
 * @return ArrayList of person
 * @throws IOException 
 * @throws JsonMappingException 
 * @throws JsonParseException 
 */

  public ArrayList<Person> getPersonInfoService(String firstName, String lastName) throws JsonParseException, JsonMappingException, IOException {
   
    return personDao.getPersonInfoDao(firstName, lastName);
  }
  
  public Person getpersons() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDao.getperson();
  }

 

}
