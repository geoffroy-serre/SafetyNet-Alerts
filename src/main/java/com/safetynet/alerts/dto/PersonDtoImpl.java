package com.safetynet.alerts.dto;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.IPersonDto;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

@Component
public class PersonDtoImpl implements IPersonDto {
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
  public ArrayList<Person> getPersonInfoDto(String firstName, String lastName)
      throws JsonParseException, JsonMappingException, IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    Person person = objectMapper.readValue(new File("data.json"), Person.class);
    Home adress = objectMapper.readValue(new File("data.json"), Home.class);
    adress.getId();
    person.setIdHome(1);
    ArrayList<Person> personList = new ArrayList<Person>();
    personList.add(person);
    logger.info("trucqui marche");
    return personList;
  }
  
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public PersonList getPersonListDto() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    DateFormat df = new SimpleDateFormat("MM-dd-YYYY");
    objectMapper.setDateFormat(df);

    PersonList personsList = objectMapper.readValue(new File("data.json"),
        PersonList.class);
    
    logger.info("PersonList retrieval");
    
//logger.info(trucs.toString());
    return personsList;
  }

}
