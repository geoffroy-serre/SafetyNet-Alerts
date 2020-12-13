package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.utils.RequestLogger;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The Class PersonController.
 */
@RestController
public class PersonController {

  private static final Logger logger = LogManager.getLogger("App");

  @Autowired
  OutPutPersonService outPutPersonService;

  @Autowired
  OutPutHomeService outPutHomeService;

  @Autowired
  OutPutMedicalRecordService outPutMedicalRecordService;

  @Autowired
  RetrieveOutPutResponseService retrieveOutPutResponseService;


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
      logger.info("Status : " + response.getStatus() + " no person for this address "+address +" "
              + result.toString());
    }

    return result;


  }
}
