package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.WorkingPersonsService;
import com.safetynet.alerts.model.OriginalPersons;
import com.safetynet.alerts.services.OriginalFileService;
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
  WorkingPersonsService workingPersonsService;

  @Autowired
  OriginalFileService originalFileService;

  /**
   * Retrieve all Person from original file.
   *
   * @return ArrayList
   */
  @GetMapping("/persons")
  public ArrayList<OriginalPersons> getPersons() {

    return originalFileService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE).getPersons();

  }


}
