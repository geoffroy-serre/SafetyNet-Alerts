package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IPersonDto;
import com.safetynet.alerts.model.PersonList;

@Component
public class PersonService {
  
  @Autowired
  private IPersonDto personDto;


  
  public PersonList getpersons() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return personDto.getPersonListDto();
  }

 

}
