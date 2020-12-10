package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutResponseService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.interfaces.WorkingMedicalRecordService;
import com.safetynet.alerts.model.*;
import java.util.ArrayList;
import java.util.HashSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FireStationController {

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
                  outPutHome.setStationNumber(null);
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

  @GetMapping("/fire")
  public OutPutHome getPersonsbyAddress(@RequestParam String address) {
    ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
    OutPutHome result = new OutPutHome();

    OutPutResponse getOutPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    for (OutPutHome outPutHome : getOutPutResponse.getHomes()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        outPutPersons = outPutHome.getPersons();
        for (OutPutPerson outPutPerson : outPutPersons) {
          for (OutPutMedicalRecord outPutMedicalRecord : getOutPutResponse.getMedicalrecords()) {
            if (outPutMedicalRecord.getIdMedicalRecord().equals(outPutPerson.getIdMedicalRecord())) {
              outPutPerson.setMedicalRecord(outPutMedicalRecord);
              outPutPerson.setBirthdate(null);
              outPutPerson.setEmail(null);
              for (OutPutFireStation outPutFireStation : getOutPutResponse.getFirestations()){
                if(outPutFireStation.getHomeList().contains(outPutHome)){
                  outPutHome.setStationNumber(outPutFireStation.getStationNumber());
                }
              }
              result= outPutHome;
            }
          }
        }
      }
    }

    return result;

  }

  @GetMapping("phoneAlert")
  public HashSet<String> getPhoneNumberByStations(@RequestParam Integer firestation){
    HashSet<String> resultPhoneNumbers = new HashSet<>();
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    for(OutPutFireStation outPutFireStation : outPutResponse.getFirestations()){
      if (outPutFireStation.getStationNumber()==firestation){
        for(OutPutHome outPutHome : outPutFireStation.getHomeList()){
          for(OutPutPerson outPutPerson : outPutHome.getPersons()){
            resultPhoneNumbers.add(outPutPerson.getPhone());
          }
        }
      }
    }

    return resultPhoneNumbers;
  }


}
