package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OriginalFirestation;
import java.util.ArrayList;

public interface OriginalFireStationService {
  ArrayList<OriginalFirestation> deleteOriginalFireStation(OriginalFirestation originalFirestation,
                                                           ArrayList<OriginalFirestation> originalFirestations);

  boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                     ArrayList<OriginalFirestation> firestations
  );

  boolean isFireStationAlreadyInFile(Integer stationNumber,
                                     ArrayList<OriginalFirestation> firestations
  );

  boolean isFireStationAlreadyInFile(String address,
                                     ArrayList<OriginalFirestation> firestations
  );

  boolean isAdressLinked(String address,
                         ArrayList<OriginalFirestation> firestations
  );

  OriginalFirestation checkIfAdressExist(ArrayList<OriginalFirestation> firestations,
                                         String address);

  /**
   * Get fireStation from original data file.
   * @return ArrayList<OriginalFirestation>
   */
  ArrayList<OriginalFirestation> getOriginalFireStations();


  OriginalFirestation getFireStationByNumberAndAddress(ArrayList<OriginalFirestation> fireStations,
                                             Integer stationNumber, String address);

  OriginalFirestation getFireStationByAddress(ArrayList<OriginalFirestation> fireStations,
                                            String address);

  OriginalFirestation getFireStationByNumber(ArrayList<OriginalFirestation> fireStations,
                                             Integer number);

  ArrayList<OriginalFirestation> getFireStationsByNumber(ArrayList<OriginalFirestation> fireStations,
                                                         Integer number);

  ArrayList<OriginalFirestation> postNewFireStation(OriginalFirestation originalFirestation,
                                                    ArrayList<OriginalFirestation> originalFirestations);
}
