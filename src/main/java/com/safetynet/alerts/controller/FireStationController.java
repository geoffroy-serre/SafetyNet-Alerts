package com.safetynet.alerts.controller;

import com.safetynet.alerts.Exceptions.*;
import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.*;
import com.safetynet.alerts.model.*;
import com.safetynet.alerts.utils.RequestLogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
public class FireStationController {

  private static final Logger logger = LogManager.getLogger("firestationController");
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
  @Autowired
  OriginalFleService originalFleService;
  @Autowired
  OriginalFireStationService originalFireStationService;
  @Autowired
  CreateWorkingFileService createWorkingFileService;

  /**
   * Deletefirestation by adress of station number.
   *
   * @param deleteFireStation DeleteFirestation
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return DeleteFireStation
   */
  @DeleteMapping("/firestation")
  DeleteFirestation deleteFireStation(@Valid @RequestBody DeleteFirestation deleteFireStation,
                                      final HttpServletResponse response,
                                      final HttpServletRequest request) {

    RequestLogger.logObjectRequest(request, "fireStationController");
    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);
    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();
    if (deleteFireStation.getStation() != null) {
      if (!originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getStation(),
              originalFirestations)) {
        response.setStatus(204);
        logger.info("Status : " + response.getStatus() + " firestation Unknown " + deleteFireStation.toString());
        return deleteFireStation;

      }
    }
    if (deleteFireStation.getAddress() != null) {
      if (!originalFireStationService.isFireStationAlreadyInFile(deleteFireStation.getAddress(),
              originalFirestations)) {
        response.setStatus(204);
        logger.info("Status : " + response.getStatus() + " firestation Unknown " + deleteFireStation.toString());
        return deleteFireStation;

      }
    }
    if (deleteFireStation.getAddress() != null) {
      OriginalFirestation originalfireStation =
              originalFireStationService.getFireStationByAddress(originalFirestations,
                      deleteFireStation.getAddress());
      originalFirestations =
              originalFireStationService.deleteOriginalFireStation(originalfireStation,
                      originalFirestations);
      originalResponse.setFirestations(originalFirestations);
    }
    if (deleteFireStation.getStation() != null) {
      originalFirestations =
              originalFireStationService.getFireStationsWithoutThisStation(originalFirestations,
                      deleteFireStation.getStation());
      OriginalFirestation stationRemain = new OriginalFirestation();
      stationRemain.setStation(deleteFireStation.getStation());
      stationRemain.setAddress("");
      originalFirestations.add(stationRemain);
      originalResponse.setFirestations(originalFirestations);

    }
    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalFirestations.isEmpty()) {
      logger.info("Status : " + response.getStatus() + deleteFireStation.getStation() + " " + deleteFireStation.getAddress() + "deleted " + deleteFireStation.toString());
    }

    return deleteFireStation;
  }

  /**
   * Post new firestation.
   *
   * @param newFiresStation OriginalFirestation
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return OriginalFireStation
   */
  @PostMapping("/firestation")
  OriginalFirestation postFireStation(@Valid @RequestBody OriginalFirestation newFiresStation,
                                      final HttpServletResponse response,
                                      final HttpServletRequest request) {

    RequestLogger.logObjectRequest(request, "fireStationController");
    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);
    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();
    if (originalFireStationService.isFireStationAlreadyInFile(newFiresStation.getStation(),
            newFiresStation.getAddress(), originalFirestations)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " person already registered " + newFiresStation.toString());
      throw new AllreadyInDatabaseException("FireStation number known",
              newFiresStation.getAddress());

    }
    if(originalFirestations.isEmpty()){
      response.setStatus(500);
      throw new EmptyDataretrievalException(newFiresStation.toString(),
              originalFirestations.toString());
    }

    originalFireStationService.postNewFireStation(newFiresStation, originalFirestations);
    originalResponse.setFirestations(originalFirestations);
    originalFleService.writeOriginalFile(originalResponse);
    createWorkingFileService.createWorkingFile();
    if (response.getStatus() == 200 && !originalFirestations.isEmpty()) {
      logger.info("Status : " + response.getStatus() + " added " + newFiresStation.toString());
    }


    return newFiresStation;

  }

  /**
   * Modify firestation.
   *
   * @param modifyFireStation OriginalFirestation
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return
   */
  @PutMapping("/firestation")
  OriginalFirestation putFireStation(@Valid @RequestBody OriginalFirestation modifyFireStation,
                                     final HttpServletResponse response,
                                     final HttpServletRequest request) {


    RequestLogger.logObjectRequest(request, "fireStationController");

    OriginalResponse originalResponse =
            originalFleService.getOriginalResponse(FilesPath.ORIGINAL_INPUT_FILE);

    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();

    if (!originalFireStationService.isFireStationAlreadyInFile(modifyFireStation.getStation(),
            modifyFireStation.getAddress(), originalFirestations)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " firestation Unknown " + modifyFireStation.toString());
      throw new NoExistingStation(modifyFireStation.getStation(), "No station known");

    } else if (!originalFireStationService.isAdressLinked(
            modifyFireStation.getAddress(), originalFirestations)) {
      response.setStatus(400);
      logger.info("Status : " + response.getStatus() + " firestation Unknown " + modifyFireStation.toString());
      throw new NoStationNumberException(modifyFireStation.getStation(), "No station known");

    } else {

      OriginalFirestation originalfireStation =
              originalFireStationService.getFireStationByAddress(originalFirestations,

                      modifyFireStation.getAddress());


      originalFirestations =
              originalFireStationService.deleteOriginalFireStation(originalfireStation,
                      originalFirestations);
      originalFirestations.add(modifyFireStation);
      originalResponse.setFirestations(originalFirestations);

      originalFleService.writeOriginalFile(originalResponse);
      createWorkingFileService.createWorkingFile();
      if (response.getStatus() == 200 && !originalFirestations.isEmpty()) {
        logger.info("Status : " + response.getStatus() + modifyFireStation.getStation() + " " + modifyFireStation.getAddress() + "modified " + modifyFireStation.toString());
      }

      return modifyFireStation;
    }
  }

  /**
   * Get persons by stationNumber.
   *
   * @param stations  ArrayList<Integer>
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return
   */
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
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " no results found for station number " + stations + " " + result.toString());
    }

    return result;

  }

  /**
   * Get personsInfo and station number by address.
   *
   * @param address String
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return
   */
  @GetMapping("/fire")
  public OutPutHome getPersonsbyAddress(@RequestParam(value = "address") String address,
                                        final HttpServletResponse response,
                                        final HttpServletRequest request) {

    RequestLogger.logRequest(request);

    OutPutHome selectedHome = outPutHomeService.getHomeByAddress(address);
    UUID wantedAddress;
    wantedAddress = selectedHome.getIdHome();

    if (wantedAddress.equals(new UUID(0L, 0L))) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " no result  for " + address + " " + selectedHome.toString());
      return new OutPutHome();

    }
    int stationNumber = outPutFireStationService.getStationNumberByHomeId(wantedAddress,
            outPutFireStationService.getFiresStations());

    outPutHomeService.setPersonsHome(outPutPersonService.getAllPerson(), selectedHome);

    selectedHome.setStationNumber(stationNumber);
    if (response.getStatus() == 200 && selectedHome.getAddress().equalsIgnoreCase(address)) {
      logger.info("Status : " + response.getStatus() + " containing " + selectedHome.toString());
    }
    if (response.getStatus() == 200 && !selectedHome.getAddress().equalsIgnoreCase(address)) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " containing no Homes");
    }
    return selectedHome;

  }

  /**
   * Get phones of persons served by given station number.
   *
   * @param firestation Integer
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return
   */
  @GetMapping("/phoneAlert")
  public HashSet<String> getPhoneNumberByStations(@RequestParam(value = "firestation") Integer
                                                          firestation,
                                                  final HttpServletResponse response,
                                                  final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    OutPutFireStation selectedFireStation =
            outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),
                    firestation);
    if (selectedFireStation == null) {
      HashSet<String> result = new HashSet<>();
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " no result for station number " + firestation + " results= " + result.toString());
      return result;
    }
    ArrayList<UUID> firestationHomes = selectedFireStation.getHomeListIds();
    ArrayList<OutPutPerson> selectedPersons =
            outPutPersonService.getPersonByHomeIds(firestationHomes);
    HashSet<String> result = outPutPersonService.getPersonsPhones(selectedPersons);
    if (response.getStatus() == 200) {
      logger.info("Status : " + response.getStatus() + " for station " + firestation + " " +
              "containing " + result.toString());
    }

    return result;
  }

  /**
   * Get persons infos for a given station number and with count of childs and adults.
   *
   * @param stationNumber int
   * @param response HttpServletResponse
   * @param request HttpServletRequest
   * @return
   */
  @GetMapping(path = "/firestation", produces = "application/json")
  public ArrayList<OutPutHome> getPersonbyStationWithFamillyStats(@RequestParam(value =
          "stationNumber") int stationNumber, final HttpServletResponse response,
                                                                  final HttpServletRequest request) {
    RequestLogger.logRequest(request);
    OutPutFireStation selectedFireStation =
            outPutFireStationService.getFireStationByNumber(outPutFireStationService.getFiresStations(),
                    stationNumber);

    if (selectedFireStation == null || selectedFireStation.getIdFirestation() == (new UUID(0L,
            0L))) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " no result for station number " + stationNumber);
      return new ArrayList<>();
    }
    ArrayList<OutPutHome> served =
            outPutHomeService.getHomeByStationNumber(outPutHomeService.getOutPutHomeList(),
                    selectedFireStation);
    if (served.isEmpty()) {
      response.setStatus(204);
      logger.info("Status : " + response.getStatus() + " Station " + stationNumber + " is empty /" +
              " don't " +
              "exist" +
              " " + served.toString());
      return new ArrayList<>();
    }
    outPutHomeService.setPersons(outPutPersonService.getAllPerson(), served);
    outPutHomeService.getCountChildrenAndAdultsforList(served);
    outPutHomeService.setStationNumberNull(served);
    if (response.getStatus() == 200) {
      logger.info("Status : " + response.getStatus() + " containing " + served.toString());
    }

    return served;
  }


}
