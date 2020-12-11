package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.OutPutHomeService;
import com.safetynet.alerts.interfaces.RetrieveOutPutResponseService;
import com.safetynet.alerts.interfaces.WorkingHomeService;
import com.safetynet.alerts.interfaces.WorkingMedicalRecordService;
import com.safetynet.alerts.model.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
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
    ArrayList<OutPutHome> selectedHomes = new ArrayList<>();
    ArrayList<OutPutFireStation> wantedFireStations = new ArrayList<>();
    HashMap<Integer, ArrayList<OutPutHome>> result = new HashMap<>();

    for (int stationNumber : stations) {
      for (OutPutFireStation outPutFireStation : outPutResponse.getFirestations()) {
        if (outPutFireStation.getStationNumber() == stationNumber) {
          wantedFireStations.add(outPutFireStation);
        }
      }
    }

    for (OutPutFireStation outPutFireStation : wantedFireStations) {
      for (UUID fireStationHomeId : outPutFireStation.getHomeListIds()) {
        for (OutPutHome outPutHome : outPutResponse.getHomes()) {
          if (outPutHome.getIdHome().equals(fireStationHomeId)) {
            outPutHome.setStationNumber(outPutFireStation.getStationNumber());
            selectedHomes.add(outPutHome);
          }
        }
      }
    }

    for (OutPutHome outPutHome : selectedHomes) {
      ArrayList<OutPutPerson> wantedPersons = new ArrayList<>();
      for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
        for (OutPutMedicalRecord outPutMedicalRecord : outPutResponse.getMedicalrecords()) {
          if (outPutHome.getIdHome().equals(outPutPerson.getIdHome())) {
            if (outPutMedicalRecord.getIdMedicalRecord().equals(outPutPerson.getIdMedicalRecord())) {
              outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
              outPutPerson.setBirthdate(null);
              outPutPerson.setEmail(null);
              outPutPerson.setMedicalRecord(outPutMedicalRecord);
              wantedPersons.add(outPutPerson);
            }
          }
        }
        outPutHome.setPersons(wantedPersons);
      }
    }

    ArrayList<OutPutFireStation> finalResult = new ArrayList<>();
    for (OutPutFireStation outPutFireStation : wantedFireStations) {
      ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
      for (OutPutHome outPutHome : selectedHomes) {

        if (outPutFireStation.getStationNumber() == outPutHome.getStationNumber()) {

          outPutHomes.add(outPutHome);
        }

      }
      outPutFireStation.setHomes(outPutHomes);
      finalResult.add(outPutFireStation);
    }


    for (OutPutFireStation outPutFireStation : finalResult) {
      for (OutPutHome outPutHome : outPutFireStation.getHomes()) {
        outPutHome.setStationNumber(null);
      }
    }
    return finalResult;

  }

  @GetMapping("/fire")
  public OutPutHome getPersonsbyAddress(@RequestParam String address) {
    ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
    OutPutHome result = new OutPutHome();

    OutPutResponse getOutPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutHome> selectedHomes = new ArrayList<>();
    UUID wantedAddress = new UUID(0L, 0L);
    int stationNumber = 0;

    for (OutPutHome outPutHome : getOutPutResponse.getHomes()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        selectedHomes.add(outPutHome);
        wantedAddress = outPutHome.getIdHome();
      }
    }

    for (OutPutFireStation outPutFireStation : getOutPutResponse.getFirestations()) {
      for (UUID outPutHome : outPutFireStation.getHomeListIds()) {
        if (wantedAddress.equals(outPutHome)) {
          stationNumber = outPutFireStation.getStationNumber();
        }
      }
    }
    for (OutPutHome outPutHome : selectedHomes) {
      ArrayList<OutPutPerson> personsInHome = new ArrayList<>();
      for (OutPutPerson outPutPerson : getOutPutResponse.getPersons()) {
        if (outPutPerson.getIdHome().equals(wantedAddress)) {
          for (OutPutMedicalRecord outPutMedicalRecord : getOutPutResponse.getMedicalrecords()) {
            if (outPutPerson.getIdMedicalRecord().equals(outPutMedicalRecord.getIdMedicalRecord())) {
              outPutPerson.setMedicalRecord(outPutMedicalRecord);
              outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
              personsInHome.add(outPutPerson);
            }
          }
        }
      }
      outPutHome.setStationNumber(stationNumber);
      outPutHome.setPersons(personsInHome);
      result = outPutHome;
    }
    for (OutPutPerson outPutperson : result.getPersons()) {
      outPutperson.setBirthdate(null);
      outPutperson.setEmail(null);
    }
    return result;

  }

  @GetMapping("/phoneAlert")
  public HashSet<String> getPhoneNumberByStations(@RequestParam Integer firestation) {
    HashSet<String> resultPhoneNumbers = new HashSet<String>();
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);

    for (OutPutFireStation outPutFireStation : outPutResponse.getFirestations()) {
      if (outPutFireStation.getStationNumber() == firestation) {
        for (UUID outPutHomIde : outPutFireStation.getHomeListIds()) {
          for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
            if (outPutPerson.getIdHome().equals(outPutHomIde)) {
              resultPhoneNumbers.add(outPutPerson.getPhone());
            }
          }
        }
      }
    }

    return resultPhoneNumbers;
  }

  @GetMapping("/firestation")
  public ArrayList<OutPutHome> getPersonbyStationWithFamillyStats(@RequestParam
                                                                          int stationNumber) {

    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutHome> result = new ArrayList<>();
    ArrayList<OutPutHome> served = new ArrayList<>();
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();

    for (OutPutFireStation outPutFireStation : outPutResponse.getFirestations()) {
      if (stationNumber == (outPutFireStation.getStationNumber())) {
        for (UUID outPutHomeId : outPutFireStation.getHomeListIds()) {
          for (OutPutHome outPutHome : outPutResponse.getHomes()) {
            if (outPutHomeId.equals(outPutHome.getIdHome())) {
              outPutHome.setStationNumber(outPutFireStation.getStationNumber());
              served.add(outPutHome);
            }
          }
        }
      }
    }
    for (OutPutHome outPutHome : served) {
      ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
      for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
        if (outPutPerson.getIdHome().equals(outPutHome.getIdHome())) {
          outPutPersons.add(outPutPerson);
        }
      }
      outPutHome.setPersons(outPutPersons);
    }

    for (OutPutHome outPutHome : served){
      int underAge = 0;
      int ofAge = 0;
      for (OutPutPerson outPutPerson : outPutHome.getPersons()){
        outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
        if (outPutPerson.getAge()< OfAgeRules.OF_AGE_FR){
          underAge++;
        }
        else if (outPutPerson.getAge()>= OfAgeRules.OF_AGE_FR){
          ofAge++;
        }
        outPutPerson.setBirthdate(null);
        outPutPerson.setEmail(null);

      }
      outPutHome.setNumberOfAdults(ofAge);
      outPutHome.setNumberOfChildren(underAge);
    }

    return served;
  }


}
