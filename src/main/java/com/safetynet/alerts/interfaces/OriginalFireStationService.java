package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalFirestation;
import java.util.ArrayList;

public interface OriginalFireStationService {
  ArrayList<OriginalFirestation> deleteOriginalFireStation(OriginalFirestation originalFirestation,
                                                           ArrayList<OriginalFirestation> originalFirestations);

  /**
   * Search for presence of a FireStation presence in a given list.
   * Search by number and Adress.
   *
   * @param stationNumber Integer
   * @param address       String
   * @param firestations  ArrayList<OriginalFirestation>>
   * @return boolean
   */
  boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                     ArrayList<OriginalFirestation> firestations
  );

  /**
   * Search for presence of Firestation in a List by given number.
   *
   * @param stationNumber Integer
   * @param firestations  ArrayList<OriginalFirestation>
   * @return boolean
   */
  boolean isFireStationAlreadyInFile(Integer stationNumber,
                                     ArrayList<OriginalFirestation> firestations
  );

  /**
   * Search for presence of a Firestation with a given adress.
   *
   * @param address      String
   * @param firestations ArrayList<OriginalFirestation>
   * @return boolean
   */

  boolean isFireStationAlreadyInFile(String address,
                                     ArrayList<OriginalFirestation> firestations
  );

  /**
   * Search is and adress is already linked to a Firestation.
   *
   * @param address      String
   * @param firestations ArrayList<OriginalFirestation>
   * @return boolean
   */
  boolean isAdressLinked(String address,
                         ArrayList<OriginalFirestation> firestations
  );

  /**
   * Search for a Firestation for an adress in the OriginalFirestation.
   *
   * @param firestations ArrayList<OriginalFirestation>
   * @param address      String
   * @return
   */
  OriginalFirestation checkIfAdressExist(ArrayList<OriginalFirestation> firestations,
                                         String address);

  /**
   * Get fireStation from original data file.
   *
   * @return ArrayList<OriginalFirestation>
   */
  ArrayList<OriginalFirestation> getOriginalFireStations();

  /**
   * Retrieve FireStation by name and Address.
   * Return new OriginalFireStation if no matches.
   *
   * @param fireStations  ArrayList<OriginalFirestation>
   * @param stationNumber Integer
   * @param address       String
   * @return OriginalFirestation
   */
  OriginalFirestation getFireStationByNumberAndAddress(ArrayList<OriginalFirestation> fireStations,
                                                       Integer stationNumber, String address);

  /**
   * Retrieve FireStation by Address.
   * Return new OriginalFireStation if no matches.
   *
   * @param fireStations ArrayList<OriginalFirestation>
   * @param address      String
   * @return OriginalFirestation
   */
  OriginalFirestation getFireStationByAddress(ArrayList<OriginalFirestation> fireStations,
                                              String address);

  /**
   * Retrieve FireStation by station number.
   * Return new OriginalFireStation if no matches.
   *
   * @param fireStations ArrayList<OriginalFirestation>
   * @param number       Integer
   * @return OriginalFirestation
   */
  OriginalFirestation getFireStationByNumber(ArrayList<OriginalFirestation> fireStations,
                                             Integer number);

  /**
   * Retrieve FireStations by station number.
   * Return empty ArrayList if no matches.
   *
   * @param fireStations ArrayList<OriginalFirestation>
   * @param number       Integer
   * @return ArrayList of OriginalFireStations
   */
  ArrayList<OriginalFirestation> getFireStationsWithoutThisStation(ArrayList<OriginalFirestation> fireStations,
                                                                   Integer number);

  /**
   * Create a new list with the OriginalFirestation you want to add.
   *
   * @param originalFirestation  OriginalFirestation
   * @param originalFirestations ArrayList<OriginalFirestation>
   * @return ArrayList of OriginalFirestations
   */
  ArrayList<OriginalFirestation> postNewFireStation(OriginalFirestation originalFirestation,
                                                    ArrayList<OriginalFirestation> originalFirestations);
}
