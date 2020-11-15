package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.HomeList;

public interface IHomeDto {
  
  public HomeList getHomeListDto() throws JsonParseException, JsonMappingException, IOException;
  
 

}
