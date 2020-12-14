package com.safetynet.alerts.controller;

import com.safetynet.alerts.Exceptions.AllreadyInDatabaseException;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.utils.RequestLogger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 * The Class PersonController.
 */
@RestController
public class PersonController {

  private static final Logger logger = LogManager.getLogger("SafetyNetAlerts personController");

  @Autowired
  OutPutPersonService outPutPersonService;
  @Autowired
  OutPutHomeService outPutHomeService;
  @Autowired
  OutPutMedicalRecordService outPutMedicalRecordService;
  @Autowired
  RetrieveOutPutResponseService retrieveOutPutResponseService;
  @Autowired
  RetrieveOriginalDataService retrieveOriginalDataService;
  @Autowired
  OriginalPersonsService originalPersonsService;
  @Autowired
  OriginalFleService originalFleService;
  @Autowired
  CreateWorkingFileService createWorkingFileService;

  @DeleteMapping("/person")
  String deletePerson(@Valid @RequestBody PersonNames personNames,
                      final HttpServletResponse response,
                      final HttpServletRequest request) {

    RequestLogger.logPostPutDeleteRequest(request,"PersonController");
    if (!outPutPersonService.isPersonAlreadyInFile(personNames.getFirstName(),
            personNames.getLastName())) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " Person unknown. Can't be deleted " + personNames.getFirstName() + " " + personNames.getLastName());

      return "Unknown person: " + personNames.getFirstName() + " " + personNames.getLastName();

    }
    OriginalResponse originalResponse =
            retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    ArrayList<OriginalPerson> originalPersons = originalResponse.getPersons();

    originalPersons = originalPersonsService.deletePerson(personNames.getFirstName(),
            personNames.getLastName(), originalPersons);
    originalResponse.setPersons(originalPersons);
    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();

    if (response.getStatus() == 200 && !originalPersons.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " " + personNames.getFirstName() + " " + personNames.getLastName() + " deleted");
    }
    return personNames.getFirstName() + " " + personNames.getLastName() + " deleted";
  }

  @PostMapping("/person")
  OriginalPerson postPerson(@Valid @RequestBody OriginalPerson newPerson,
                            final HttpServletResponse response,
                            final HttpServletRequest request) {


    RequestLogger.logPostPutDeleteRequest(request, "personController");
    if (outPutPersonService.isPersonAlreadyInFile(newPerson.getFirstName(),
            newPerson.getLastName())) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " person already registered " + newPerson.toString());
      throw new AllreadyInDatabaseException(newPerson.getFirstName(), newPerson.getLastName());

    }else {
      OriginalResponse originalResponse =
              originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);

      ArrayList<OriginalPerson> originalPersons = originalResponse.getPersons();


      originalPersonsService.postNewPerson(newPerson, originalPersons);

      originalResponse.setPersons(originalPersons);
      originalFleService.writeOriginalFile(originalResponse);
      createWorkingFileService.createWorkingFile();
      if (response.getStatus() == 200 && !originalPersons.isEmpty()) {
        logger.info("Status : " + response.getStatus() + " added " + newPerson.toString());
      }
    }

    return newPerson;

  }

  @PutMapping("/person")
  OriginalPerson putPerson(@Valid @RequestBody OriginalPerson modifyPerson,
                           final HttpServletResponse response,
                           final HttpServletRequest request) {


    RequestLogger.logPostPutDeleteRequest(request, "personController");
    if (!outPutPersonService.isPersonAlreadyInFile(modifyPerson.getFirstName(),
            modifyPerson.getLastName())) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " person unknown. Can't be modified " + modifyPerson.toString());
      throw new AllreadyInDatabaseException(modifyPerson.getFirstName(),
              modifyPerson.getLastName());


    }

    OriginalResponse originalResponse =
            retrieveOriginalDataService.retrieveOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    ArrayList<OriginalPerson> originalPersons = originalResponse.getPersons();


    OriginalPerson originalPerson =
            originalPersonsService.getOriginalPersonByFirstAndLastName(modifyPerson.getFirstName(),
                    modifyPerson.getLastName(), originalPersons);


    originalPersons = originalPersonsService.deleteOriginalPerson(originalPerson, originalPersons);
    originalPersons.add(modifyPerson);
    originalResponse.setPersons(originalPersons);

    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalPersons.isEmpty()) {
      logger.info("Status : " + response.getStatus() + modifyPerson.getFirstName() + " " + modifyPerson.getLastName() + "modified " + modifyPerson.toString());
    }


    return modifyPerson;

  }


  @GetMapping("/communityEmail")
  public HashSet<String> getEmailforACity(@RequestParam(value = "city") String city,
                                          final HttpServletResponse response,
                                          final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    HashSet<UUID> idHomes = outPutHomeService.getHomesByCity(city);
    HashSet<String> output = outPutPersonService.getPersonsEmailByCity(idHomes);
    if (response.getStatus() == 200 && !output.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " containing " + output.toString());
    }
    if (response.getStatus() == 200 && output.isEmpty()) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + "No result found. Output = " + output.toString());
    }
    return output;

  }

  @GetMapping("/personInfo")
  public ArrayList<OutPutPerson> getPersonInfo(@RequestParam(value = "firstName") String firstName,
                                               @RequestParam(value = "lastName") String lastName,
                                               final HttpServletResponse response,
                                               final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    ArrayList<OutPutPerson> selectedPersons =
            outPutPersonService.getPersonsByFirstAndLastName(firstName, lastName);

    ArrayList<OutPutMedicalRecord> outPutMedicalRecords =
            outPutMedicalRecordService.getAllMedicalRecords();
    ArrayList<OutPutPerson> personsWithMedicalRecord =
            outPutPersonService.setMedicalRecordForPersons(selectedPersons,
                    outPutMedicalRecords);

    ArrayList<OutPutHome> selectedHomes = outPutHomeService.getOutPutHomeList();

    ArrayList<OutPutPerson> result =
            outPutPersonService.setPersonsHome(personsWithMedicalRecord, selectedHomes);
    outPutPersonService.setPhoneNull(result);

    if (response.getStatus() == 200 && !result.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " containing " + result.toString());
    }
    if (response.getStatus() == 200 && result.isEmpty()) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " no result found for " + firstName + " " + lastName + " " + result.toString());
    }
    return result;

  }

  @GetMapping("/childAlert")
  public OutPutChild getChilds(@RequestParam(value = "address") String address,
                               final HttpServletResponse response,
                               final HttpServletRequest request) {
    RequestLogger.logRequest(request);

    OutPutHome selectedHome = outPutHomeService.getHomeByAddress(address);
    ArrayList<OutPutPerson> personsForSelectedHome =
            outPutPersonService.getPersonsByHomeID(selectedHome);

    selectedHome.setPersons(personsForSelectedHome);
    OutPutChild result = outPutPersonService.getCountedTypeOfPersons(selectedHome.getPersons());
    if (response.getStatus() == 200 && !result.getChild().isEmpty() && result.getFamilly().isEmpty() || response.getStatus() == 200 && result.getChild().isEmpty() && !result.getFamilly().isEmpty()) {
      logger.info("Status : " + response.getStatus() + " containing " + result.toString());
    }
    if (response.getStatus() == 200 && result.getChild().isEmpty() && result.getFamilly().isEmpty()) {
      logger.info("Status : " + response.getStatus() + " no person for this address " + address + " "
              + result.toString());
    }

    return result;


  }
}
