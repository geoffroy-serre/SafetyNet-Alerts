package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.IFireStationDao;
import com.safetynet.alerts.model.FireStation;
import com.safetynet.alerts.model.FireStationList;
import com.safetynet.alerts.utils.OriginalInputFile;

/**
 * The Class FireStationDaoImpl.
 */
@Repository
public class FireStationDaoImpl implements IFireStationDao{
  
  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");


  /**
   * Gets the fire station list dao.
   *
   * @param filePath the file path
   * @return the fire station list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public FireStationList getFireStationListDao(String filePath) {
    
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    objectMapper.registerModule(new JavaTimeModule());
    
    FireStationList fireStationList = new FireStationList();
    try {
      fireStationList = objectMapper.readValue(new File(filePath),
          FireStationList.class);
    } catch (JsonParseException e) {
      logger.error("JsonPArseException getting FireStation List ",e);
    } catch (JsonMappingException e) {
      logger.error("JsonMappingException getting FireStation List ",e);
    } catch (IOException e) {
      logger.error("IOException getting FireStation List ",e);
    }
    
    logger.info("FireStationList retrieval done");

    return fireStationList;

  }


  /**
   * Post fire station mapping dao.
   *
   * @return the fire station
   */
  @Override
  public FireStation postFireStationMappingDao() {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Update fire station number dao.
   *
   * @return the fire station
   */
  @Override
  public FireStation updateFireStationNumberDao() {
    // TODO Auto-generated method stub
    return null;
  }


  /**
   * Delete fire station mapping dao.
   *
   * @return the fire station
   */
  @Override
  public FireStation deleteFireStationMappingDao() {
    // TODO Auto-generated method stub
    return null;
  }

}
