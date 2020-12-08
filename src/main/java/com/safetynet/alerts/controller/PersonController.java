package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.OutPutPersonService;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.model.WorkingPerson;
import com.safetynet.alerts.services.OriginalFileService;
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


  @GetMapping("/communityEmail")
  public HashSet<String> getEmailforACity(@RequestParam String city) {
    HashSet<String> output = new HashSet<>();
    ArrayList<WorkingPerson> persons = outPutPersonService.getAllPerson();
    for (WorkingPerson currentPerson : persons) {
      String cityy = outPutHomeService.getHomeById(currentPerson.getHomeID()).getCity();

      if (outPutHomeService.getHomeById(currentPerson.getHomeID()).getCity().equalsIgnoreCase(city)) {
        output.add(currentPerson.getEmail());
      }

    }
    return output;

  }


}
