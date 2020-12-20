package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;
import java.util.UUID;

public interface OutPutFireStationService {
  /**
   * Verify presence of OutPutFireStation with given parameters.
   *
   * @param stationNumber Integer
   * @param address       String
   * @param firestations  ArrayList<OutPutFireStation>
   * @return boolean
   */
  boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                     ArrayList<OutPutFireStation> firestations
  );

  /**
   * Search for station numbner for given home Id.
   *
   * @param homeId       UUID
   * @param fireStations ArrayList<OutPutFireStation>
   * @return int
   */
  int getStationNumberByHomeId(UUID homeId, ArrayList<OutPutFireStation> fireStations);

  /**
   * Set station home number to null, to avoid their display in result.
   *
   * @param fireStations ArrayList<OutPutFireStation>
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations);

  /**
   * Get All FireStation with OutPut formatting.
   *
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> getFiresStations();

  /**
   * Get OutPut FireStation by station number.
   * Return new OutPut FireStationif nothing found
   *
   * @param fireStations  ArrayList<OutPutFireStation>
   * @param stationNumber Integer
   * @return OutPutFireStation
   */
  OutPutFireStation getFireStationByNumber(ArrayList<OutPutFireStation> fireStations,
                                           Integer stationNumber);

  /**
   * Get FireStations by station numbers
   *
   * @param fireStations  ArrayList<OutPutFireStation>
   * @param stationNumber Integer
   * @return ArrayList of OutPutFire
   */
  ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                       ArrayList<Integer> stationNumber);

  /**
   * Set Homes adress city and zip for given list with given homes
   *
   * @param fireStations ArrayList<OutPutFireStation>
   * @param homes        ArrayList<OutPutHome> homes
   * @return ArrayList of OutPutFireStation
   */
  ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                        ArrayList<OutPutHome> homes);

  /**
   * Set Hom information in given OutPutFireStation
   *
   * @param fireStation ArrayList<OutPutFireStation>
   * @param homes       ArrayList<OutPutHome> homes
   * @return OutPutFireStation
   */
  OutPutFireStation setHome(OutPutFireStation fireStation,
                            ArrayList<OutPutHome> homes);
}
