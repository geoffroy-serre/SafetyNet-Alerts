package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
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
  @Autowired
  RetrieveOutPutResponseService retrieveOutPutResponseService;
  @Autowired
  WorkingMedicalRecordService workingMedicalRecordService;


  @GetMapping("/flood/stations")
  public ArrayList<OutPutFireStation> getPersonsByStation(@RequestParam ArrayList<Integer> stations) {
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutFireStation> outPutFireStations = new ArrayList<>();
    for (Integer stationNumber : stations) {

      for (OutPutFireStation outPutFireStation : outPutResponse.getFirestations()) {
        if (outPutFireStation.getStationNumber() == stationNumber) {
          for (OutPutHome outPutHome : outPutFireStation.getHomeList()) {
            for (OutPutPerson outPutPerson : outPutHome.getPersons()) {
              for (OutPutMedicalRecord outPutMedicalRecord : outPutResponse.getMedicalrecords()) {
                if (outPutMedicalRecord.getIdMedicalRecord().equals(outPutPerson.getIdMedicalRecord())) {
                  outPutPerson.setMedicalRecord(outPutMedicalRecord);
                  outPutPerson.setBirthdate(null);
                  outPutPerson.setEmail(null);
                  outPutPerson.setIdMedicalRecord(null);
                }
              }
            }
          }
          outPutFireStations.add(outPutFireStation);
        }

      }
    }

    return outPutFireStations;
  }


}
