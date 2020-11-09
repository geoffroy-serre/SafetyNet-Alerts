package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.Person;



@Component
public class PersonDao {
  private static final Logger logger = LogManager.getLogger("App");
  /**
   * Retrieve personInfo from given Json file.
   * 
   * @param firstName
   * @param lastName
   * @return ArrayList of Person
   * @throws IOException
   * @throws JsonMappingException
   * @throws JsonParseException
   */
  public ArrayList<Person> getPersonInfoDao(String firstName, String lastName)
      throws JsonParseException, JsonMappingException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Person person = objectMapper.readValue(new File("data.json"), Person.class);
    Home adress = objectMapper.readValue(new File("data.json"), Home.class);
    adress.setId(1);
    adress.getId();
    person.setIdHome(1);
    ArrayList<Person> personList = new ArrayList<Person>();
    personList.add(person);
    logger.info("trucqui marche");
    return personList;
  }

  
  @JsonIgnoreProperties(ignoreUnknown = true)
  public Person getperson()
      throws JsonParseException, JsonMappingException, IOException, NoSuchFileException {
    ObjectMapper objectMapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("MM-dd-YYYY");
    objectMapper.setDateFormat(df);
    Path pathArray = Paths.get("daa.json");
    logger.info("trucqui marche");
    
    Person trucs = objectMapper.readValue(
        Files.newBufferedReader(pathArray, StandardCharsets.UTF_8),
        Person.class);
    

    return trucs;
  }

}
