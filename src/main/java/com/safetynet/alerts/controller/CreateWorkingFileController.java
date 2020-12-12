package com.safetynet.alerts.controller;

import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkingFileController {

@Autowired
CreateWorkingFileService createWorkingFileService;

  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {
    createWorkingFileService.createWorkingFile();
  }


}
