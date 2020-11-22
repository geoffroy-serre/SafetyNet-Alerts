package com.safetynet.alerts.controller;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.HomeList;
import com.safetynet.alerts.services.HomeService;
import com.safetynet.alerts.utils.WorkingFileOuput;

@RestController
public class HomeController {

  /** The Constant logger. */
  private static final Logger logger = LogManager.getLogger("App");

  /** The home service. */
  @Autowired
  HomeService homeService;

  /**
   * Gets the home info controller.
   *
   * @return the home info controller
   */
  @GetMapping("/homeInfo")
  public HashSet<Home> getHomeInfoController() {

    return homeService.getHomeService(WorkingFileOuput.getWorkingInputFile());

  }

}
