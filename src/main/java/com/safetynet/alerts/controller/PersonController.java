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
  WorkingHomeService workingHomeService;

  @Autowired
  OutPutHomeService outPutHomeService;

  @Autowired
  OutPutMedicalRecordService outPutMedicalRecordService;

  @Autowired
  RetrieveOutPutResponseService retrieveOutPutResponseService;


  @GetMapping("/communityEmail")
  public HashSet<String> getEmailforACity(@RequestParam String city) {
    HashSet<String> output = new HashSet<>();
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    HashSet<UUID> idHomes = new HashSet<>();
    for (OutPutHome outPutHome : outPutResponse.getHomes()) {
      if (outPutHome.getCity().equalsIgnoreCase(city)) {
        idHomes.add(outPutHome.getIdHome());
      }
    }
    for (UUID idHome : idHomes) {
      for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
        if (outPutPerson.getIdHome().equals(idHome)) {
          output.add(outPutPerson.getEmail());
        }
      }

    }

    return output;

  }

  @GetMapping("/personInfo")
  public ArrayList<OutPutPerson> getPersonInfo(@RequestParam String firstName,
                                               @RequestParam String lastName) {
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutPerson> preResult = new ArrayList<>();
    for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
      if (outPutPerson.getFirstName().equalsIgnoreCase(firstName) && outPutPerson.getLastName().equalsIgnoreCase(lastName)) {
        for (OutPutMedicalRecord outPutMedicalRecord : outPutResponse.getMedicalrecords()) {
          if (outPutPerson.getIdMedicalRecord().equals(outPutMedicalRecord.getIdMedicalRecord())) {
            outPutPerson.setMedicalRecord(outPutMedicalRecord);
            outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
            outPutPerson.setBirthdate(null);
            outPutPerson.setPhone(null);
            preResult.add(outPutPerson);
          }
        }
      }
    }
    ArrayList<OutPutPerson> result = new ArrayList<>();
    for (OutPutPerson outPutPerson : preResult) {
      for (OutPutHome outPutHome : outPutResponse.getHomes()) {
        if (outPutHome.getIdHome().equals(outPutPerson.getIdHome())) {
          outPutPerson.setHome(outPutHome);
          result.add(outPutPerson);
        }
      }
    }

    return result;

  }

  @GetMapping("/childAlert")
  public OutPutChild getChilds(@RequestParam String address) {

    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutPerson> family = new ArrayList<>();
    ArrayList<OutPutPerson> underAge = new ArrayList<>();
    ArrayList<OutPutPerson> personsForSelectedHome = new ArrayList<>();
    OutPutChild outPutChild = new OutPutChild();
    OutPutHome selectedHome = new OutPutHome();
    for (OutPutHome outPutHome : outPutResponse.getHomes()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        selectedHome = outPutHome;
      }
    }
    for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
      if (selectedHome.getIdHome().equals(outPutPerson.getIdHome())) {
        outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
        personsForSelectedHome.add(outPutPerson);
      }
    }
    selectedHome.setPersons(personsForSelectedHome);

    for (OutPutPerson outPutPerson : selectedHome.getPersons()) {
      boolean isUnderAge = false;
      outPutPerson.setEmail(null);
      outPutPerson.setPhone(null);
      outPutPerson.setBirthdate(null);
      if (outPutPerson.getAge() < OfAgeRules.OF_AGE_FR) {
        isUnderAge = true;
      }
      if (isUnderAge) {
        underAge.add(outPutPerson);
      } else if (!isUnderAge) {
        family.add(outPutPerson);
      }
    }
    outPutChild.setChild(underAge);
    outPutChild.setFamilly(family);
    return outPutChild;
  }
}
