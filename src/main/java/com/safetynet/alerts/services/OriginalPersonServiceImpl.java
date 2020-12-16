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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalPersonServiceImpl implements OriginalPersonsService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  final Logger logger = LogManager.getLogger("OriginalPersonServiceImpl");

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalPerson> postNewPerson(OriginalPerson originalPerson,
                                                 ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering postNewPerson ");
    boolean isPresent = false;
    for (OriginalPerson currentPerson : originalPersons) {
      if (originalPerson.getFirstName().equals(currentPerson.getFirstName()) &&
              originalPerson.getLastName().equals(originalPerson.getLastName())) {
        isPresent = true;
      }
    }
    if (isPresent) {
      logger.debug("Not match found postNewPerson return empty list ");
      return new ArrayList<OriginalPerson>();
    }
    ArrayList<OriginalPerson> originalPersonsResult = originalPersons;
    originalPersonsResult.add(originalPerson);
    logger.debug("Success postNewPerson ");
    return originalPersonsResult;
  }


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalPerson> getOriginalPersons(OriginalResponse originalResponse) {
    return originalResponse.getPersons();
  }

  @Override
  /**
   * @inheritDoc
   */
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

  @Override
  /**
   * @inheritDoc
   */
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

  @Override
  /**
   * @inheritDoc
   */
  public OriginalPerson replacePersonData(OriginalPerson source) {
    logger.debug("Entering replacePersonData " + source.toString());
    OriginalPerson result = new OriginalPerson();
    BeanUtils.copyProperties(source, result);
    logger.debug("Success replacePersonData " + result.toString());

    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalPerson> deleteOriginalPerson(OriginalPerson originalPerson,
                                                        ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering deleteOriginalPerson ");
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!originalPerson.getFirstName().equals(currentPerson.getFirstName()) && !originalPerson.getLastName().equals(currentPerson.getLastName())) {
        results.add(currentPerson);
      }
    }
    logger.debug("Success deleteOriginalPerson ");
    return results;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalPerson> deletePerson(String firstName, String lastName,
                                                ArrayList<OriginalPerson> originalPersons) {
    logger.debug("Entering deletePerson ");
    ArrayList<OriginalPerson> results = new ArrayList<>();
    for (OriginalPerson currentPerson : originalPersons) {
      if (!currentPerson.getFirstName().equals(firstName) && !currentPerson.getLastName().equals(lastName)) {
        results.add(currentPerson);
      }
    }
    logger.debug("Success deletePerson ");
    return results;
  }


}
