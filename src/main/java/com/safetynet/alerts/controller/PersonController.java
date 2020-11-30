package com.safetynet.alerts.controller;

import java.util.ArrayList;

import com.safetynet.alerts.services.PersonService;
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

  @GetMapping("/person")
  public ArrayList<?> getPersons() {

    return personService.getPersonsService();

  }


}
