package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.services.WorkingFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateWorkingFileController {



  @GetMapping("/createWorkingFile")
  public void createWorkingFile() {
    WorkingFileService workingFileService = new WorkingFileService();
     workingFileService.createWorkingFile(FilesPath.ORIGINAL_INPUT_FILE);

  }
}
