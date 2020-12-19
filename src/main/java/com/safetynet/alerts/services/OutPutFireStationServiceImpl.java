package com.safetynet.alerts.services;

import com.safetynet.alerts.constants.FilesPath;
import com.safetynet.alerts.interfaces.OutPutFireStationService;
import com.safetynet.alerts.interfaces.RetrieveOutPutDataRepository;
import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutPutFireStationServiceImpl implements OutPutFireStationService {

  final Logger logger = LogManager.getLogger("OutPutFireStationServiceImpl");
  @Autowired
  RetrieveOutPutDataRepository retrieveOutPutDataRepository;

  @Override
  /**
   * @inheritDoc
   */
  public boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                            ArrayList<OutPutFireStation> firestations
  ) {
    logger.debug("Entering isFireStationAlreadyInFile ");
    boolean isAlreadyInFile = false;
    OutPutFireStation selectedFireStations =
            getFireStationByNumber(firestations, stationNumber);
    if ( selectedFireStations.getStationNumber()==stationNumber) {
      isAlreadyInFile = true;
    }
    logger.debug("Success isFireStationAlreadyInFile ");
    return isAlreadyInFile;
  }

  @Override
  /**
   * @inheritDoc
   */
  public int getStationNumberByHomeId(UUID homeId, ArrayList<OutPutFireStation> fireStations) {
    logger.debug("Entering getStationNumberByHomeId ");
    int result = 0;
    for (OutPutFireStation outPutFireStation : fireStations) {
      for (UUID idHome : outPutFireStation.getHomeListIds()) {
        if (idHome.equals(homeId)) {
          result = outPutFireStation.getStationNumber();
        }
      }
    }
    logger.debug("Success getStationNumberByHomeId ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations) {
    logger.debug("Entering setStationNumberHomesToNull ");
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
    logger.debug("Success setStationNumberHomesToNull ");

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
    logger.debug("Entering getFireStationByNumber ");

    for (OutPutFireStation outPutFireStation : fireStations) {
      if (outPutFireStation.getStationNumber() == stationNumber) {
        logger.debug("Match found getFireStationByNumber ");
        return outPutFireStation;
      }
    }
    logger.debug("Not Match found getFireStationByNumber return null ");
    return new OutPutFireStation();
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                              ArrayList<Integer> stationNumber) {
    logger.debug("Entering getFireStationByNumbers ");
    ArrayList<OutPutFireStation> result = new ArrayList<>();

    for (OutPutFireStation outPutFireStation : fireStations) {
      for (Integer station : stationNumber) {
        if (outPutFireStation.getStationNumber() == station) {
          result.add(outPutFireStation);
        }
      }
    }
    logger.debug("Success getFireStationByNumber ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                               ArrayList<OutPutHome> homes) {
    logger.debug("Entering setHomes ");
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
    logger.debug("Success setHomes ");
    return result;
  }

  @Override
  /**
   * @inheritDoc
   */
  public OutPutFireStation setHome(OutPutFireStation fireStation,
                                   ArrayList<OutPutHome> homes) {
    logger.debug("Entering setHome ");
    OutPutFireStation result = new OutPutFireStation();

    ArrayList<OutPutHome> outPutHomes = new ArrayList<>();
    for (OutPutHome outPutHome : homes) {
      if (fireStation.getStationNumber() == outPutHome.getStationNumber()) {
        outPutHomes.add(outPutHome);
      }
    }
    fireStation.setHomes(outPutHomes);
    result = fireStation;
    logger.debug("Success setHome ");
    return result;
  }


}
