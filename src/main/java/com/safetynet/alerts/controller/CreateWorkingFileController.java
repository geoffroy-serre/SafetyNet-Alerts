package com.safetynet.alerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.services.CreateWorkingFileService;

@RestController
public class CreateWorkingFileController {

  @Autowired
  CreateWorkingFileService cw;
  
  
  @GetMapping("/popopupu")
  public void testPOpo() {
     
   cw.createWorkingFileWithAssociatedProcessedData();
  }
}
