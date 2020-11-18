package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.model.HomeList;

@Service
public class HomeService {
  
  @Autowired
  private IHomeDao homeDto;


  
  public HomeList getHomeService() throws JsonParseException, JsonMappingException, NoSuchFileException, IOException 
  {
    return homeDto.getHomeListDao();
  }

 

}
