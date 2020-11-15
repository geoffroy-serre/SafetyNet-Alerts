package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.FireStationList;

public interface IFireStationDto {
  
  public FireStationList getFireStationListDto() throws JsonParseException, JsonMappingException, IOException;
  
 

}
