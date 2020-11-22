package com.safetynet.alerts.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;

/**
 * The Interface IHomeDao.
 */
public interface IHomeDao {
  
  /**
   * Gets the home list dao.
   *
   * @return the home list dao
   */
  public HashSet<Home> getHomeListDao(String filePath);
  
 

}
