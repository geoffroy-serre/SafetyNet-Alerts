package com.safetynet.alerts.controller;

import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import com.safetynet.alerts.model.OutPutPerson;
import com.safetynet.alerts.utils.RequestLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


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
  public ArrayList<OutPutFireStation> getPersonsByStation(@RequestParam(value = "stations")
                                                                  ArrayList<Integer> stations,
                                                          final HttpServletResponse response,
                                                          final HttpServletRequest request) {
    RequestLogger.logRequest(request);

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
    if (response.getStatus() == 200 && !result.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " containing " + result.toString());
    }
    if (response.getStatus() == 200 && result.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " no results found for station number "+stations+" " + result.toString());
    }

    return result;

  }

  @GetMapping("/fire")
  public OutPutHome getPersonsbyAddress(@RequestParam(value = "address") String address,
                                        final HttpServletResponse response,
                                        final HttpServletRequest request) {

    RequestLogger.logRequest(request);

    OutPutHome selectedHome =outPutHomeService.getHomeByAddress(address);
    UUID wantedAddress;
    wantedAddress = selectedHome.getIdHome();

  if(wantedAddress.equals(new UUID(0L,0L))){
    logger.info("Status : " + response.getStatus() + " no result  for "+address+" " + selectedHome.toString());
    return new OutPutHome();

  }
    int stationNumber = outPutFireStationService.getStationNumberByHomeId(wantedAddress,
            outPutFireStationService.getFiresStations());

    outPutHomeService.setPersonsHome(outPutPersonService.getAllPerson(), selectedHome);

    selectedHome.setStationNumber(stationNumber);
    if (response.getStatus() == 200) {
      logger.info("Status : " + response.getStatus() + " containing " + selectedHome.toString());
    }
    return selectedHome;

  }

  @GetMapping("/phoneAlert")
  public HashSet<String> getPhoneNumberByStations(@RequestParam(value = "firestation") Integer
                                                          firestation,
                                                  final HttpServletResponse response,
                                                  final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    OutPutFireStation selectedFireStation =
            outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),
                    firestation);
    if(selectedFireStation==null){
      HashSet<String> result = new HashSet<>();
      logger.info("Status : " + response.getStatus() + " no result for station number " + firestation +" results= "+result.toString());
      return result;
    }
    ArrayList<UUID> firestationHomes = selectedFireStation.getHomeListIds();
    ArrayList<OutPutPerson> selectedPersons =
            outPutPersonService.getPersonByHomeIds(firestationHomes);
    HashSet<String> result = outPutPersonService.getPersonsPhones(selectedPersons);
    if (response.getStatus() == 200) {
      logger.info("Status : " + response.getStatus() + " for station "+firestation+" containing " + result.toString());
    }

    return result;
  }

  @GetMapping("/firestation")
  public ArrayList<OutPutHome> getPersonbyStationWithFamillyStats(@RequestParam(value =
          "stationNumber") int stationNumber, final HttpServletResponse response,
                                                                  final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    OutPutFireStation selectedFireStation =
            outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),
                    stationNumber);
    if(selectedFireStation==null){
      logger.info("Status : " + response.getStatus() + " no result for station number " + stationNumber);
      return new ArrayList<OutPutHome>();
    }
    ArrayList<OutPutHome> served =
            outPutHomeService.getHomeByStationNumber(outPutHomeService.getOutPutHomeList(),
                    selectedFireStation);

    outPutHomeService.setPersons(outPutPersonService.getAllPerson(), served);
    outPutHomeService.getCountChildrenAndAdultsforList(served);
    outPutHomeService.setStationNumberNull(served);
    if (response.getStatus() == 200) {
      logger.info("Status : " + response.getStatus() + " containing " + served.toString());
    }

    return served;
  }


}
