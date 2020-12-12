package com.safetynet.alerts.controller;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.constants.OfAgeRules;
import com.safetynet.alerts.interfaces.*;
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
  OutPutHomeService outPutHomeService;
  @Autowired
  OutPutFireStationService outPutFireStationService;

  @Autowired
  OutPutPersonService outPutPersonService;

  @Autowired
  RetrieveOutPutResponseService retrieveOutPutResponseService;

  @Autowired
  OutPutMedicalRecordService outPutMedicalRecordService;


  @GetMapping("/flood/stations")
  public ArrayList<OutPutFireStation> getPersonsByStation(@RequestParam ArrayList<Integer> stations) {
    logger.info("Launching flood station controller");

    ArrayList<OutPutFireStation> wantedFireStations =
            outPutFireStationService.getFireStationByNumbers(outPutFireStationService.getFiresStations(), stations);
    ArrayList<OutPutHome> selectedHomes = outPutHomeService.getHomesbyIds(wantedFireStations,
            outPutHomeService.getOutPutHomeList());
    ArrayList<UUID> homesId = outPutHomeService.getHomesIds(selectedHomes);
    ArrayList<OutPutPerson> selectedPersons =
            outPutPersonService.getPersonByHomeIds(homesId);
    ArrayList<OutPutPerson> personsSetted =
            outPutPersonService.setMedicalRecordForPersons(selectedPersons,
                    outPutMedicalRecordService.getAllMedicalRecords());

    outPutPersonService.setEmailToNull(personsSetted);

    ArrayList<OutPutHome> homesWithPersons = outPutHomeService.setPersons(personsSetted,
            selectedHomes);

    ArrayList<OutPutFireStation> result = outPutFireStationService.setHomes(wantedFireStations,
            homesWithPersons);

    logger.info("Returning expected result for flood station controller");
    return result;

  }

  @GetMapping("/fire")
  public OutPutHome getPersonsbyAddress(@RequestParam String address) {
    logger.info("Launching fire controller");
    ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
    OutPutHome result = new OutPutHome();

    OutPutResponse getOutPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutHome> selectedHomes = new ArrayList<>();
    UUID wantedAddress = new UUID(0L, 0L);
    int stationNumber = 0;
    logger.debug("Get requested Home for given Address ine fire controller");
    for (OutPutHome outPutHome : getOutPutResponse.getHomes()) {
      if (outPutHome.getAddress().equalsIgnoreCase(address)) {
        selectedHomes.add(outPutHome);
        wantedAddress = outPutHome.getIdHome();
      }
    }
    logger.debug("Setting fire station number in fire controller");
    for (OutPutFireStation outPutFireStation : getOutPutResponse.getFirestations()) {
      for (UUID outPutHome : outPutFireStation.getHomeListIds()) {
        if (wantedAddress.equals(outPutHome)) {
          stationNumber = outPutFireStation.getStationNumber();
        }
      }
    }
    logger.debug("Populate personsList for given Home fire controller");
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
    logger.debug("Set birthdate and email to null  to avoid display fire controller");
    for (OutPutPerson outPutperson : result.getPersons()) {
      outPutperson.setBirthdate(null);
      outPutperson.setEmail(null);
    }
    logger.info("Returning result Fire controller");
    return result;

  }

  @GetMapping("/phoneAlert")
  public HashSet<String> getPhoneNumberByStations(@RequestParam Integer firestation) {
    logger.info("Launching phoneAlert");

    OutPutFireStation selectedFireStation =
            outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),
                    firestation);
    ArrayList<UUID> firestationHomes = selectedFireStation.getHomeListIds();
    ArrayList<OutPutPerson> selectedPersons =
            outPutPersonService.getPersonByHomeIds(firestationHomes);
    HashSet<String> result = outPutPersonService.getPersonsPhones(selectedPersons);

    logger.info("Returning phones list phoneAlert controller");
    return result;
  }

  @GetMapping("/firestation")
  public ArrayList<OutPutHome> getPersonbyStationWithFamillyStats(@RequestParam
                                                                          int stationNumber) {

    logger.info("Launching firestation controller");
    OutPutResponse outPutResponse =
            retrieveOutPutResponseService.retrieveOutPutResponse(FilesPath.WORKING_INPUT_FILE);
    ArrayList<OutPutHome> result = new ArrayList<>();
    ArrayList<OutPutHome> served = new ArrayList<>();
    ArrayList<OutPutPerson> selectedPersons = new ArrayList<>();

    logger.debug("Adding served home for given station in list firestation controller");
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
    logger.debug("Adding persons to served home for given station in list firestation controller");
    for (OutPutHome outPutHome : served) {
      ArrayList<OutPutPerson> outPutPersons = new ArrayList<>();
      for (OutPutPerson outPutPerson : outPutResponse.getPersons()) {
        if (outPutPerson.getIdHome().equals(outPutHome.getIdHome())) {
          outPutPersons.add(outPutPerson);
        }
      }
      outPutHome.setPersons(outPutPersons);
    }
    logger.debug("counting children and adult for given homes firestation controller");
    for (OutPutHome outPutHome : served) {
      int underAge = 0;
      int ofAge = 0;
      for (OutPutPerson outPutPerson : outPutHome.getPersons()) {
        outPutPerson.setAge(Period.between(outPutPerson.getBirthdate(), LocalDate.now()).getYears());
        if (outPutPerson.getAge() < OfAgeRules.OF_AGE_FR) {
          underAge++;
        } else if (outPutPerson.getAge() >= OfAgeRules.OF_AGE_FR) {
          ofAge++;
        }
        outPutPerson.setBirthdate(null);
        outPutPerson.setEmail(null);

      }
      outPutHome.setNumberOfAdults(ofAge);
      outPutHome.setNumberOfChildren(underAge);
    }

    logger.info("Returning results for firestation controller");
    return served;
  }


}
