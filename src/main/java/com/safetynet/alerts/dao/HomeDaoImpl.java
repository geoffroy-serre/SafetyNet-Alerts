package com.safetynet.alerts.dao;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.interfaces.IHomeDao;
import com.safetynet.alerts.model.HomeList;

/**
 * The Class HomeDaoImpl.
 */
@Repository
public class HomeDaoImpl implements IHomeDao{

  /**
   * Gets the home list dao.
   *
   * @return the home list dao
   * @throws JsonParseException the json parse exception
   * @throws JsonMappingException the json mapping exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @Override
  public HomeList getHomeListDao()
      throws JsonParseException, JsonMappingException, IOException {
    // TODO Auto-generated method stub
    return null;
  }

}
