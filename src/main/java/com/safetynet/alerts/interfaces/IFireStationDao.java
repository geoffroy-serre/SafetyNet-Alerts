package com.safetynet.alerts.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;

/**
 * The Interface IFireStationDao.
 */
public interface IFireStationDao {
  
  /**
   * Gets the fire station list dao.
   *
   * @param filePath the file path
   * @return the fire station list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public FireStationList getFireStationListDao(String filePath) throws JsonParseException, JsonMappingException, IOException;

  /**
   * Post fire station mapping dao.
   *
   * @return the fire station
   */
  public FireStation postFireStationMappingDao();

  /**
   * Update fire station number dao.
   *
   * @return the fire station
   */
  public FireStation updateFireStationNumberDao();

  /**
   * Delete fire station mapping dao.
   *
   * @return the fire station
   */
  public FireStation deleteFireStationMappingDao();

  
  
 

}
