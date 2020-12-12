package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
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
  public HashSet<String> getEmailforACity(@RequestParam String city) {
    logger.info("Launching communityEmail controller");
    HashSet<UUID> idHomes = outPutHomeService.getHomesByCity(city);
    HashSet<String> output = outPutPersonService.getPersonsEmailByCity(idHomes);
    logger.info("Returning result communityEmail controller");
    return output;

  }

  @GetMapping("/personInfo")
  public ArrayList<OutPutPerson> getPersonInfo(@RequestParam String firstName,
                                               @RequestParam String lastName) {
    logger.info("Launch personInfo controller");
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

    logger.info("Returning result for personInfo controller");
    return result;

  }

  @GetMapping("/childAlert")
  public OutPutChild getChilds(@RequestParam String address) {
    logger.info("Launching childAlert controller");

    OutPutHome selectedHome = outPutHomeService.getHomeByAddress(address);
    ArrayList<OutPutPerson> personsForSelectedHome =
            outPutPersonService.getPersonsByHomeID(selectedHome);

    selectedHome.setPersons(personsForSelectedHome);
    logger.info("Returning result childAlert controller");

    return outPutPersonService.getCountedTypeOfPersons(selectedHome.getPersons());


  }
}
