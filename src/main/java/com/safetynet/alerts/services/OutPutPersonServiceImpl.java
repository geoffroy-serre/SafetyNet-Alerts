package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.OutPutPersonService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutPersonServiceImpl implements OutPutPersonService {
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;
  final Logger logger = LogManager.getLogger("OutPutPersonServiceImpl");


  @Override
  /**
   * @inheritDoc
   */
  public boolean isPersonAlreadyInFile(String firstName, String lastName) {
    logger.debug("Entering isPersonAlreadyInFile ");

    boolean isAlreadyInFile = false;
    ArrayList<OutPutPerson> selectedPersons =
            getPersonsByFirstAndLastName(firstName, lastName);
    if (selectedPersons.size() >= 1) {
      isAlreadyInFile = true;
    }
    logger.debug("Success isPersonAlreadyInFile ");
    return isAlreadyInFile;
  }


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> setEmailToNull(ArrayList<OutPutPerson> persons) {
    logger.debug("Entering setEmailToNull ");
    ArrayList<OutPutPerson> results = new ArrayList<>();
    for (OutPutPerson outPutPerson : persons) {
      outPutPerson.setEmail(null);
      results.add(outPutPerson);
    }
    logger.debug("SSuccess setEmailToNull ");
    return results;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> getPersonByHomeIds(ArrayList<UUID> homeIds) {
    logger.debug("Entering getPersonByHomeIds ");

    ArrayList<OutPutPerson> result = new ArrayList<>();
    for (OutPutPerson outPutPerson : getAllPerson()) {
      for (UUID currentHomeId : homeIds) {
        if (currentHomeId.equals(outPutPerson.getIdHome())) {
          result.add(outPutPerson);
        }
      }
    }
    logger.debug("Success getPersonByHomeIds ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public HashSet<String> getPersonsPhones(ArrayList<OutPutPerson> persons) {
    logger.debug("Entering getPersonsPhones ");
    HashSet<String> result = new HashSet<>();
    for (OutPutPerson outPutPerson : persons) {
      result.add(outPutPerson.getPhone());
    }
    logger.debug("Success getPersonsPhones ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> setPersonsHome(ArrayList<OutPutPerson> persons,
                                                ArrayList<OutPutHome> homes) {
    logger.debug("Entering setPersonsHome ");
    ArrayList<OutPutPerson> result = new ArrayList<>();
    for (OutPutPerson outPutPerson : persons) {
      for (OutPutHome outPutHome : homes) {
        if (outPutHome.getIdHome().equals(outPutPerson.getIdHome())) {
          outPutPerson.setHome(outPutHome);
          result.add(outPutPerson);
        }
      }
    }
    logger.debug("Success setPersonsHome ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> setMedicalRecordForPersons(ArrayList<OutPutPerson> outPutPersons
          , ArrayList<OutPutMedicalRecord> outPutMedicalRecords) {
    logger.debug("Entering setMedicalRecordForPersons ");
    ArrayList<OutPutPerson> results = new ArrayList<>();
    for (OutPutPerson outPutPerson : outPutPersons) {
      for (OutPutMedicalRecord outPutMedicalRecord : outPutMedicalRecords) {
        if (outPutPerson.getIdMedicalRecord().equals(outPutMedicalRecord.getIdMedicalRecord())) {
          outPutPerson.setMedicalRecord(outPutMedicalRecord);
          outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
          results.add(outPutPerson);
        }
      }
      if (outPutPerson.getMedicalRecord() == null) {
        outPutPerson.setMedicalRecord(new OutPutMedicalRecord());
        results.add(outPutPerson);
      }
    }
    logger.debug("Success setMedicalRecordForPersons ");
    return results;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> setPhoneNull(ArrayList<OutPutPerson> persons) {
    logger.debug("Entering setPhoneNull ");
    ArrayList<OutPutPerson> result = new ArrayList<>();
    for (OutPutPerson outPutPerson : persons) {
      outPutPerson.setPhone(null);
      result.add(outPutPerson);
    }
    logger.debug("Success setPhoneNull ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> getPersonsByFirstAndLastName(String firstName, String lastName) {
    logger.debug("Entering getPersonsByFirstAndLastName ");
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();

    for (OutPutPerson outPutPerson : getAllPerson()) {
      if (outPutPerson.getFirstName().equalsIgnoreCase(firstName) && outPutPerson.getLastName().equalsIgnoreCase(lastName)) {
        selectedPersons.add(outPutPerson);
      }
    }
    logger.debug("Success getPersonsByFirstAndLastName ");
    return selectedPersons;
  }

  @Override
  /**
   * @inheritDoc
   */
  public HashSet<String> getPersonsEmailByCity(HashSet<UUID> homeIds) {
    logger.debug("Entering getPersonsEmailByCity ");
    HashSet<String> output = new HashSet<>();
    for (UUID idHome : homeIds) {
      for (OutPutPerson outPutPerson : getAllPerson()) {
        if (outPutPerson.getIdHome().equals(idHome)) {
          output.add(outPutPerson.getEmail());
        }
      }
    }
    logger.debug("Success getPersonsEmailByCity ");
    return output;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutChild getCountedTypeOfPersons(ArrayList<OutPutPerson> persons) {
    logger.debug("Entering getCountedTypeOfPersons ");
    ArrayList<OutPutPerson> family = new ArrayList<>();
    ArrayList<OutPutPerson> underAge = new ArrayList<>();
    OutPutChild outPutChild = new OutPutChild();
    for (OutPutPerson outPutPerson : persons) {
      boolean isUnderAge = false;
      outPutPerson.setEmail(null);
      outPutPerson.setPhone(null);
      if (outPutPerson.getAge() < OfAgeRules.OF_AGE_FR) {
        isUnderAge = true;
      }
      if (isUnderAge) {
        underAge.add(outPutPerson);

      } else if (!isUnderAge) {
        family.add(outPutPerson);
      }
      isUnderAge = false;
    }
    outPutChild.setChild(underAge);
    outPutChild.setFamilly(family);
    logger.debug("Success getCountedTypeOfPersons ");
    return outPutChild;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> getPersonsByHomeID(OutPutHome selectedHome) {
    logger.debug("Entering getPersonsByHomeID ");
    ArrayList<OutPutPerson> personsForSelectedHome = new ArrayList();
    for (OutPutPerson outPutPerson : getAllPerson()) {
      if (selectedHome.getIdHome().equals(outPutPerson.getIdHome())) {
        if (outPutPerson.getBirthdate() != null) {
          outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(),
                  LocalDate.now()).getYears());
          personsForSelectedHome.add(outPutPerson);
        }
      }
    }
    logger.debug("Success getPersonsByHomeID ");
    return personsForSelectedHome;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> getAllPerson() {
    ArrayList<OutPutPerson> output =
            retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getPersons();
    return output;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutPerson transformWorkingIntoOutPut(WorkingPerson inputPerson) {
    logger.debug("Entering transformWorkingIntoOutPut ");
    OutPutPerson result = new OutPutPerson();

    result.setPhone(inputPerson.getPhone());
    result.setAge(inputPerson.getAge());
    result.setEmail(inputPerson.getEmail());
    result.setFirstName(inputPerson.getFirstName());
    result.setLastName(inputPerson.getLastName());
    result.setBirthdate(inputPerson.getBirthdate());
    logger.debug("Success transformWorkingIntoOutPut ");
    return result;


  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutPerson> transformWorkingIntoOutPut(ArrayList<WorkingPerson> inputPersons) {
    logger.debug("Entering transformWorkingIntoOutPut ");
    ArrayList<OutPutPerson> output = new ArrayList<>();
    for (WorkingPerson wp : inputPersons) {
      if (wp.getBirthdate() != null) {
        OutPutPerson result = new OutPutPerson();
        result.setPhone(wp.getPhone());
        result.setAge(wp.getAge());
        result.setEmail(wp.getEmail());
        result.setFirstName(wp.getFirstName());
        result.setLastName(wp.getLastName());
        result.setBirthdate(wp.getBirthdate());
        output.add(result);
      }
    }
    logger.debug("Success transformWorkingIntoOutPut ");
    return output;
  }
}
