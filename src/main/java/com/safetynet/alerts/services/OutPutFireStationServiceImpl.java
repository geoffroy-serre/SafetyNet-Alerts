package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutFireStationService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutFireStationServiceImpl implements OutPutFireStationService {

  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                            ArrayList<OutPutFireStation> firestations
  ) {
    boolean isAlreadyInFile = false;
    OutPutFireStation selectedFireStations =
            getFireStationByNumber(firestations,stationNumber);
    if (selectedFireStations != null) {
      isAlreadyInFile = true;
    }
    return isAlreadyInFile;
  }

  @Override
  /**
   * @inheritDoc
   */
  public int getStationNumberByHomeId (UUID homeId, ArrayList<OutPutFireStation> fireStations){
    int result = 0;
    for(OutPutFireStation outPutFireStation : fireStations){
      for (UUID idHome : outPutFireStation.getHomeListIds()){
        if(idHome.equals(homeId)){
          result = outPutFireStation.getStationNumber();
        }
      }
    }
    return result;
  }
  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations) {
    ArrayList<OutPutFireStation> result = new ArrayList<>();
    for (OutPutFireStation outPutFireStation : fireStations) {
      ArrayList<OutPutHome> homes = new ArrayList<>();
      for (OutPutHome outPutHome : outPutFireStation.getHomes()) {
        outPutHome.setStationNumber(null);
        homes.add(outPutHome);
      }
      outPutFireStation.setHomes(homes);
      result.add(outPutFireStation);
    }
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> getFiresStations() {
    return retrieveOutPutDataRepository.getOutPutData(FilesPath.WORKING_INPUT_FILE).getFirestations();
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutFireStation getFireStationByNumber(ArrayList<OutPutFireStation> fireStations,
                                                  Integer stationNumber) {
    for (OutPutFireStation outPutFireStation : fireStations) {
      if (outPutFireStation.getStationNumber() == stationNumber) {
        return outPutFireStation;
      }
    }
    return null;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                              ArrayList<Integer> stationNumber) {
    ArrayList<OutPutFireStation> result = new ArrayList<>();

    for (OutPutFireStation outPutFireStation : fireStations) {
      for (Integer station : stationNumber) {
        if (outPutFireStation.getStationNumber() == station) {
          result.add(outPutFireStation);
        }
      }
    }
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                               ArrayList<OutPutHome> homes) {
    ArrayList<OutPutFireStation> result = new ArrayList<>();
    for (OutPutFireStation outPutFireStation : fireStations) {
      ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
      for (OutPutHome outPutHome : homes) {
        if (outPutFireStation.getStationNumber() == outPutHome.getStationNumber()) {
          outPutHomes.add(outPutHome);
        }
      }
      outPutFireStation.setHomes(outPutHomes);
      result.add(outPutFireStation);
    }
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutFireStation setHome(OutPutFireStation fireStation,
                                   ArrayList<OutPutHome> homes) {
    OutPutFireStation result = new OutPutFireStation();

    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      if (fireStation.getStationNumber() == outPutHome.getStationNumber()) {
        outPutHomes.add(outPutHome);
      }
    }
    fireStation.setHomes(outPutHomes);
    result = fireStation;

    return result;
  }


}
