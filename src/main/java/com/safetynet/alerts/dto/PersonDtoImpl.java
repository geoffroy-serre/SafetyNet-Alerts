package com.safetynet.alerts.dto;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.interfaces.IPersonDto;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonList;
import com.safetynet.alerts.utils.OriginalInputFile;

@Component
public class PersonDtoImpl implements IPersonDto {
  private static final Logger logger = LogManager.getLogger("App");

  
  @Override
  @JsonIgnoreProperties(ignoreUnknown = true)
  public PersonList getPersonListDto() throws JsonParseException, JsonMappingException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    DateFormat df = new SimpleDateFormat("MM-dd-YYYY");
    objectMapper.setDateFormat(df);

    PersonList personsList = objectMapper.readValue(new File(OriginalInputFile.getOriginalInputFile()),
        PersonList.class);
    
    logger.info("PersonList retrieval");
    
//logger.info(trucs.toString());
    return personsList;
  }

}
