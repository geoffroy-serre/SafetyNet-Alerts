package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingPerson;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import org.apache.commons.lang.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingPersonsServiceImpl implements WorkingPersonsService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  final Logger logger = LogManager.getLogger("OriginalFireStationServiceImpl");


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<WorkingPerson> reestablishCase(Collection<WorkingPerson> workingPersons) {
    logger.debug("Entering reestablishCase ");
    ArrayList<WorkingPerson> result = new ArrayList<>();
    for (WorkingPerson workingPerson : workingPersons) {
      WorkingPerson processingPerson = new WorkingPerson();
      BeanUtils.copyProperties(workingPerson, processingPerson);
      processingPerson.setFirstName(WordUtils.capitalize(workingPerson.getFirstName()));
      processingPerson.setLastName(WordUtils.capitalize(workingPerson.getLastName()));
      result.add(processingPerson);
    }
    logger.debug("Success reestablishCase ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public HashMap<String, WorkingPerson> getWorkingPersonsHashMap() {
    logger.debug("Entering getWorkingPersonsHashMap ");
    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashMap<String, WorkingPerson> workingPersonsHashMap = new HashMap<>();

    for (OriginalPerson originalPerson : originalResponse.getPersons()) {
      WorkingPerson workingPerson = new WorkingPerson();
      workingPerson.setId(UUID.randomUUID());
      workingPerson.setEmail(originalPerson.getEmail());
      workingPerson.setFirstName(originalPerson.getFirstName());
      workingPerson.setLastName(originalPerson.getLastName());
      workingPerson.setPhone(originalPerson.getPhone());
      String createdKey =
              originalPerson.getFirstName() + "," + originalPerson.getLastName() + "," + originalPerson.getAddress() + "," + originalPerson.getCity() + "," + originalPerson.getZip();
      workingPersonsHashMap.put(createdKey, workingPerson);
    }
    logger.debug("Success getWorkingPersonsHashMap ");
    return workingPersonsHashMap;
  }


}
