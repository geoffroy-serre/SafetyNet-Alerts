package com.safetynet.alerts.controller;

import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @Autowired
  OutPutHomeService outPutHomeService;

  @GetMapping("/home")
  public ArrayList<OutPutHome> getAllHomes() {
    return outPutHomeService.getOutPutHomeList();
  }


}
