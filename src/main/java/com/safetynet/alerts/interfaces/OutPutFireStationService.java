package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;
import java.util.UUID;

public interface OutPutFireStationService {
  /**
   * Verify presence of OutPutFireStation with given parameters.
   *
   * @param stationNumber
   * @param address
   * @param firestations
   * @return boolean
   */
  boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                     ArrayList<OutPutFireStation> firestations
  );

  /**
   * Search for station numbner for given home Id.
   *
   * @param homeId
   * @param fireStations
   * @return int
   */
  int getStationNumberByHomeId(UUID homeId, ArrayList<OutPutFireStation> fireStations);

  /**
   * Set station home number to null, to avoid their display in result.
   *
   * @param fireStations
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations);

  /**
   * Get All FireStation with OutPut formatting
   *
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> getFiresStations();

  /**
   * Get OutPut FireStation by station number
   * Return new OutPut FireStationif nothing found
   *
   * @param fireStations
   * @param stationNumber
   * @return OutPutFireStation
   */
  OutPutFireStation getFireStationByNumber(ArrayList<OutPutFireStation> fireStations,
                                           Integer stationNumber);

  /**
   * Get FireStations by station numbers
   *
   * @param fireStations
   * @param stationNumber
   * @return ArrayList of OutPutFire
   */
  ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                       ArrayList<Integer> stationNumber);

  /**
   * Set Homes adress city and zip for given list with given homes
   *
   * @param fireStations
   * @param homes
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                        ArrayList<OutPutHome> homes);

  /**
   * Set Hom information in given OutPutFireStation
   *
   * @param fireStation
   * @param homes
   * @return OutPutFireStation
   */
  OutPutFireStation setHome(OutPutFireStation fireStation,
                            ArrayList<OutPutHome> homes);
}
