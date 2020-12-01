package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.Response;
import com.safetynet.alerts.interfaces.RetrieveData;
import com.safetynet.alerts.interfaces.WorkingFileService;
import com.safetynet.alerts.model.WorkingHome;
import com.safetynet.alerts.services.PersonService;
import com.safetynet.alerts.services.WorkingFileServiceImpl;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class PersonController.
 */
@RestController
public class PersonController {


  private static final Logger logger = LogManager.getLogger("App");


  @Autowired
  PersonService personService;

  @Autowired
  WorkingFileService worksp;

  @Autowired
  RetrieveData retrieveData;



  /**
   * Retrieve all Person from original file.
   *
   * @return ArrayList
   */
  @GetMapping("/person")
  public ArrayList<WorkingHome> getPersons() {
    Response response = retrieveData.getData(FilesPath.ORIGINAL_INPUT_FILE);
    return worksp.createWorkingHomes(response);

  }


}
