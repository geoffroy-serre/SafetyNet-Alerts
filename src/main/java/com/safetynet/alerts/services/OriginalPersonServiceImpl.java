package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalPersonsService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalPersonServiceImpl implements OriginalPersonsService {
  final Logger logger = LogManager.getLogger("OriginalPersonServiceImpl");
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalPerson> postNewPerson(OriginalPerson originalPerson,
                                                 ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering postNewPerson ");
    boolean isPresent = false;

    for (OriginalPerson currentPerson : originalPersons) {
      if (originalPerson.getFirstName().equalsIgnoreCase(currentPerson.getFirstName()) &&
              originalPerson.getLastName().equalsIgnoreCase(currentPerson.getLastName())) {
        isPresent = true;
      }
    }
    if (isPresent) {
      logger.debug("Not match found postNewPerson return empty list ");
      return originalPersons;
    }
    ArrayList<OriginalPerson> originalPersonsResult = originalPersons;
    originalPersonsResult.add(originalPerson);
    logger.debug("Success postNewPerson ");
    return originalPersonsResult;
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalPerson> getOriginalPersons(OriginalResponse originalResponse) {
    return originalResponse.getPersons();
  }

  /**
   * @inheritDoc
   */
  @Override
  public HashMap<String, OriginalPerson> getOriginalPersonHashMap() {
    logger.debug("Entering getOriginalPersonHashMap ");
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, OriginalPerson> originalPersonHashMap = new HashMap<>();

    for (OriginalPerson originalPersons : originalResponse.getPersons()) {
      originalPersonHashMap.put(originalPersons.getFirstName() + "," + originalPersons.getLastName(),
              originalPersons);
    }
    logger.debug("Success getOriginalPersonHashMap ");
    return originalPersonHashMap;
  }

  /**
   * @inheritDoc
   */
  @Override
  public OriginalPerson getOriginalPersonByFirstAndLastName(String firstName, String lastName,
                                                            ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering getOriginalPersonByFirstAndLastName ");

    for (OriginalPerson originalPerson : originalPersons) {
      if (originalPerson.getFirstName().equalsIgnoreCase(firstName) && originalPerson.getLastName().equalsIgnoreCase(lastName)) {
        logger.debug("Match found getOriginalPersonByFirstAndLastName ");
        return originalPerson;
      }
    }
    logger.debug("No Match found getOriginalPersonByFirstAndLastName return new OriginalPerson ");
    return new OriginalPerson();
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalPerson> deleteOriginalPerson(OriginalPerson originalPerson,
                                                        ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering deleteOriginalPerson ");
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!originalPerson.getFirstName().equals(currentPerson.getFirstName()) || !originalPerson.getLastName().equals(currentPerson.getLastName())) {
        results.add(currentPerson);
      }
    }
    logger.debug("Success deleteOriginalPerson ");
    return results;
  }

  /**
   * @inheritDoc
   */
  @Override
  public ArrayList<OriginalPerson> deletePersonbyFirstAndLastNames(String firstName,
                                                                   String lastName,
                                                                   ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering deletePerson ");
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!currentPerson.getFirstName().equalsIgnoreCase(firstName) && !currentPerson.getLastName().equalsIgnoreCase(lastName)) {
        results.add(currentPerson);
      }
    }
    logger.debug("Success deletePerson ");
    return results;
  }


}
