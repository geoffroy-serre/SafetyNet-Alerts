package com.safetynet.alerts.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFleService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalResponse;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFileServiceImpl implements OriginalFleService {
  final Logger logger = LogManager.getLogger("OriginalFileServiceImpl");
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalData;

  /**
   * @inheritDoc
   */
  @Override
  public OriginalResponse getOriginalResponse(String constantOriginalDataFile) {
    return retrieveOriginalData.getOriginalData(constantOriginalDataFile);
  }

  /**
   * @inheritDoc
   */
  @Override
  public void writeOriginalFile(OriginalResponse originalResponse) {

    logger.debug("Begin write of originalFile");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES,
            true);
    objectMapper.registerModule(new JavaTimeModule());
    try {
      logger.debug("Writing Original File with ");
      objectMapper.writeValue(new File(FilesPath.ORIGINAL_INPUT_FILE), originalResponse);

    } catch (IOException e) {
      logger.error("Problem creating original file with " + originalResponse.toString() + " as " +
                      "parameter",
              e);
    }
    logger.debug("Original  written");
  }
}
