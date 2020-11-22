package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;

/**
 * The Class HomeDaoImpl.
 */
@Repository
public class HomeDaoImpl implements IHomeDao{

  private static final Logger logger = LogManager.getLogger("App");
  /**
   * Gets the home list dao.
   *
   * @return the home list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public HashSet<Home> getHomeListDao(String filePath) {
    HomeList homeList = new HomeList();
    
    
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
        true);
    objectMapper.registerModule(new JavaTimeModule());

   try {
    homeList = objectMapper.readValue(new File(filePath),HomeList.class);
  } catch (JsonParseException e) {
    logger.error("JsonPArseException getting HOme List ",e);
  } catch (JsonMappingException e) {
    logger.error("JsonMappingException getting HOme List ",e);
  } catch (IOException e) {
    logger.error("IOException getting HOme List ",e);
  }

    logger.info("HomeList retrieval");
    HashSet<Home> list = new HashSet<Home>(homeList.getHome());
    

    return list;
    
  }

}
