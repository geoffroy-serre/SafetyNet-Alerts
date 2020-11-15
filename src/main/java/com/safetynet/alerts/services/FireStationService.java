package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IFireStationDto;
import com.safetynet.alerts.model.FireStationList;

@Component
public class FireStationService {
  
  @Autowired
  private IFireStationDto fireStationDto;


  
  public FireStationList getFireStationList() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return fireStationDto.getFireStationListDto();
  }

 

}
