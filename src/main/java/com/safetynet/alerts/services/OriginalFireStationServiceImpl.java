package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OriginalFireStationService;
import com.safetynet.alerts.interfaces.RetrieveOriginalDataRepository;
import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalPerson;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.OutPutFireStation;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalFireStationServiceImpl implements OriginalFireStationService {
  @Autowired
  OriginalResponse originalResponse;
  @Autowired
  RetrieveOriginalDataRepository retrieveOriginalDataRepository;

  @Override
  public ArrayList<OriginalFirestation> deleteOriginalFireStation(OriginalFirestation originalFirestation,
                                                        ArrayList<OriginalFirestation> originalFirestations) {
    ArrayList<OriginalFirestation> results = new ArrayList<>();
    for (OriginalFirestation currentFireStation : originalFirestations) {
      if (!originalFirestation.getAddress().equalsIgnoreCase(currentFireStation.getAddress())) {
        results.add(currentFireStation);
      }
    }
    return results;
  }


  @Override
  public boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByNumberAndAddress(firestations,stationNumber,address);
    if (selectedFireStations != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }

  @Override
  public boolean isFireStationAlreadyInFile(Integer stationNumber,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByNumber(firestations,stationNumber);
    if (selectedFireStations != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }
  @Override
  public boolean isFireStationAlreadyInFile(String address,
                                            ArrayList<OriginalFirestation> firestations
  ) {
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            getFireStationByAddress(firestations,address);
    if (selectedFireStations != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }

  @Override
  public boolean isAdressLinked(String address,
                                ArrayList<OriginalFirestation> firestations
  ) {
    boolean isAlreadyInFile = false;
    OriginalFirestation selectedFireStations =
            checkIfAdressExist(firestations,address);
    if (selectedFireStations != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }

  @Override
  public OriginalFirestation checkIfAdressExist(ArrayList<OriginalFirestation> firestations,
                                                String address){
    for(OriginalFirestation originalFirestation : firestations){
      if(originalFirestation.getAddress().equalsIgnoreCase(address)){
        return originalFirestation;
      }
    }
    return null;
  }


  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OriginalFirestation> getOriginalFireStations() {

    originalResponse =
            retrieveOriginalDataRepository.getOriginalData(FilesPath.ORIGINAL_INPUT_FILE);
    HashSet<OriginalFirestation> originalFireStations = new HashSet<>();

    for (OriginalFirestation originalFireStation : originalResponse.getFirestations()) {
      originalFireStations.add(originalFireStation);
    }
    ArrayList<OriginalFirestation> result = new ArrayList<OriginalFirestation>(originalFireStations);
    return result;
  }

  @Override
  public OriginalFirestation getFireStationByNumberAndAddress(ArrayList<OriginalFirestation> fireStations,
                                                              Integer stationNumber, String address) {
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation() == stationNumber && originalFireStation.getAddress().equalsIgnoreCase(address)) {
        return originalFireStation;
      }
    }
    return null;
  }

  @Override
  public OriginalFirestation getFireStationByAddress(ArrayList<OriginalFirestation> fireStations,
                                                     String address) {
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getAddress().equalsIgnoreCase(address)) {
        return originalFireStation;
      }
    }
    return null;
  }
  @Override
  public OriginalFirestation getFireStationByNumber(ArrayList<OriginalFirestation> fireStations,
                                                     Integer number) {
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation()== number) {
        return originalFireStation;
      }
    }
    return null;
  }

  @Override
  public ArrayList<OriginalFirestation> getFireStationsByNumber(ArrayList<OriginalFirestation> fireStations,
                                                    Integer number) {
    ArrayList<OriginalFirestation> originalFirestations = new ArrayList<>();
    for (OriginalFirestation originalFireStation : fireStations) {
      if (originalFireStation.getStation()!= number) {
        originalFirestations.add(originalFireStation);
      }
    }
    return originalFirestations;
  }



  @Override
  public ArrayList<OriginalFirestation> postNewFireStation(OriginalFirestation originalFirestation,
                                                           ArrayList<OriginalFirestation> originalFirestations) {
    boolean isPresent = false;
    for (OriginalFirestation currentFirestation : originalFirestations) {
      if (originalFirestation.getAddress().equals(currentFirestation.getAddress()) &&
              originalFirestation.getStation()== currentFirestation.getStation()) {
        isPresent = true;
      }
    }
    if (isPresent) {
      return new ArrayList<OriginalFirestation>();
    }
    ArrayList<OriginalFirestation> originalFirestationsResult = originalFirestations;
    originalFirestationsResult.add(originalFirestation);
    return originalFirestationsResult;
  }


}
