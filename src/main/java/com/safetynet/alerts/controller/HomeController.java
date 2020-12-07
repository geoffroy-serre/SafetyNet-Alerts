package com.safetynet.alerts.controller;

import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @Autowired
  WorkingHomeService workingHomeService;

  @GetMapping("/home")
  public ArrayList<WorkingHome> getAllHomes(){
    return workingHomeService.getWorkingHomesArrayList();
  }


}
