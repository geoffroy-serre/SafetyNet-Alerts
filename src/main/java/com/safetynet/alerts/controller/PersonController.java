package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
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
    ArrayList<WorkingPerson> persons = outPutPersonService.getAllPerson();
    for (WorkingPerson currentPerson : persons) {
      if (workingHomeService.getHomeById(currentPerson.getHomeID()).getCity().equalsIgnoreCase(city)) {
        output.add(currentPerson.getEmail());
      }
    }
    return output;

  }

  @GetMapping("/personInfo")
  public ArrayList<OutPutPerson> getPersonInfo(@RequestParam String firstName,
                                               @RequestParam String lastName) {
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutPerson> result = new ArrayList<>();
    for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
      if (outPutPerson.getFirstName().equalsIgnoreCase(firstName) && outPutPerson.getLastName().equalsIgnoreCase(lastName)) {
        for (OutPutMedicalRecord outPutMedicalRecord : outPutResponse.getMedicalrecords()) {
          if (outPutPerson.getIdMedicalRecord().equals(outPutMedicalRecord.getIdMedicalRecord())) {
            outPutPerson.setMedicalRecord(outPutMedicalRecord);
            outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
            outPutPerson.setBirthdate(null);
            outPutPerson.setPhone(null);
            result.add(outPutPerson);
          }
        }
      }
    }

    return result;

  }

  @GetMapping("/childAlert")
  public OutPutChild getChilds(@RequestParam String address) {

    ArrayList<OutPutHome> outPutHomes =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE).getHomes();
    ArrayList<OutPutPerson> family = new ArrayList<>();
    ArrayList<OutPutPerson> underAge = new ArrayList<>();
    OutPutChild outPutChild = new OutPutChild();
    for (OutPutHome outPutHome : outPutHomes) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {

        for (OutPutPerson outPutPerson : outPutHome.getPersons()) {
          boolean isUnderAge = false;
  outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
          if (outPutPerson.getAge() < OfAgeRules.OF_AGE_FR) {
            isUnderAge = true;
          }
          if (isUnderAge) {
            outPutPerson.setBirthdate(null);
            outPutPerson.setPhone(null);
            outPutPerson.setEmail(null);
            underAge.add(outPutPerson);
            isUnderAge = false;
          } else if (!isUnderAge) {
            outPutPerson.setBirthdate(null);
            family.add(outPutPerson);
          }
        }
      }
      if (!underAge.isEmpty()) {
        outPutChild.setChild(underAge);
        outPutChild.setFamilly(family);
      }
    }

    return outPutChild;
  }
}
