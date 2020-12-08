package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.OutPutPersonService;
import com.safetynet.alerts.interfaces.WorkingFirestationsService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.services.OutPutHomeServiceImpl;
import com.safetynet.alerts.services.OutPutPersonServiceImpl;
import com.safetynet.alerts.services.WorkingFireStationServiceImpl;
import com.safetynet.alerts.services.WorkingHomeServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FireStationController {

  /**
   * The Constant logger.
   */
  private static final Logger logger = LogManager.getLogger("App");
  @Autowired
  WorkingHomeService workingHomeService;
  @Autowired
  OutPutHomeService outPutHomeService;

  @GetMapping("/flood/stations")
  public ArrayList<OutPutFireStation> getPersonsByStation(@RequestParam ArrayList<Integer> stations) {
    WorkingFirestationsService workingFirestationsService = new WorkingFireStationServiceImpl();
    OutPutPersonService outPutPersonService = new OutPutPersonServiceImpl();

    HashMap<Integer, WorkingFireStation> workingFireStationHashMap =
            workingFirestationsService.getWorkingFireStationHashMap();
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();

    for (Integer stationNumber : stations) {
      if (workingFireStationHashMap.containsKey(stationNumber)) {
       
      }
    }
    return outPutFireStations;
  }


}
