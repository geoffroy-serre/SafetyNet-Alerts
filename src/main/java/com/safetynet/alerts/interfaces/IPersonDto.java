package com.safetynet.alerts.interfaces;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

public interface IPersonDto {
  
  public PersonList getPersonListDto() throws JsonParseException, JsonMappingException, IOException;
  public ArrayList<Person> getPersonInfoDto(String firstName, String lastName)
      throws JsonParseException, JsonMappingException, IOException;

}
