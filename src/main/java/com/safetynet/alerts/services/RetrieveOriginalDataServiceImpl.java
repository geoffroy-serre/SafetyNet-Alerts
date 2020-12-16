package com.safetynet.alerts.services;

import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataService;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalMedicalrecord;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveOriginalDataServiceImpl implements RetrieveOriginalDataService {
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;
  final Logger logger = LogManager.getLogger("RetrieveOriginalDataServiceImpl");

  @Override
  /**
   * @inheritDoc
   */
  public OriginalResponse retrieveOriginalData(String filepath) {
    logger.debug("Entering retrieveOriginalData");

    OriginalResponse originalResponse = retrieveOriginalDataRepository.getOriginalData(filepath);


    HashSet<OriginalPerson> originalPersonHashSet = new HashSet<>();
    HashSet<OriginalFirestation> originalFirestationHashet = new HashSet<>();
    HashSet<OriginalMedicalrecord> originalMedicalrecordHashSet = new HashSet<>();
    OriginalResponse result = new OriginalResponse();

    for (OriginalPerson originalPerson : originalResponse.getPersons()) {
      OriginalPerson personProcessed = new OriginalPerson();
      BeanUtils.copyProperties(originalPerson, personProcessed);
      personProcessed.setAddress(originalPerson.getAddress().toLowerCase());
      personProcessed.setFirstName(originalPerson.getFirstName().toLowerCase());
      personProcessed.setLastName(originalPerson.getLastName().toLowerCase());
      personProcessed.setCity(originalPerson.getCity().toLowerCase());
      personProcessed.setZip(originalPerson.getZip().toLowerCase());
      originalPersonHashSet.add(personProcessed);
    }
    for (OriginalFirestation originalFirestation : originalResponse.getFirestations()) {
      OriginalFirestation processedFirestation = new OriginalFirestation();
      BeanUtils.copyProperties(originalFirestation, processedFirestation);
      processedFirestation.setAddress(originalFirestation.getAddress().toLowerCase());
      originalFirestationHashet.add(processedFirestation);
    }

    for (OriginalMedicalrecord originalMedicalrecord : originalResponse.getMedicalrecords()) {
      OriginalMedicalrecord processedMedicalrecord = new OriginalMedicalrecord();
      BeanUtils.copyProperties(originalMedicalrecord, processedMedicalrecord);
      processedMedicalrecord.setFirstName(originalMedicalrecord.getFirstName().toLowerCase());
      processedMedicalrecord.setLastName((originalMedicalrecord.getLastName().toLowerCase()));
      originalMedicalrecordHashSet.add(processedMedicalrecord);

    }
    ArrayList<OriginalPerson> originalPersons = new ArrayList<>(originalPersonHashSet);
    ArrayList<OriginalFirestation> originalFirestations =
            new ArrayList<>(originalFirestationHashet);
    ArrayList<OriginalMedicalrecord> originalMedicalrecords =
            new ArrayList<>(originalMedicalrecordHashSet);
    result.setFirestations(originalFirestations);
    result.setPersons(originalPersons);
    result.setMedicalrecords(originalMedicalrecords);

    logger.debug("Success retrieveOriginalData");
    return result;

  }
}
