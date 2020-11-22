package com.safetynet.alerts.services;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;

/**
 * The Class HomeService.
 */
@Service
public class HomeService {
  
  /** The home dto. */
  @Autowired
  private IHomeDao homeDto;


  
  /**
   * Gets the home service.
   *
   * @return the home service
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws NoSuchFileException the no such file exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public HashSet<Home> getHomeService(String filePath) 
  {
    return homeDto.getHomeListDao(filePath);
  }

 

}
