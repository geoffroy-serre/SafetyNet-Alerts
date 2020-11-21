package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.HomeList;

/**
 * The Interface IHomeDao.
 */
public interface IHomeDao {
  
  /**
   * Gets the home list dao.
   *
   * @return the home list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public HomeList getHomeListDao() throws JsonParseException, JsonMappingException, IOException;
  
 

}
