package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;
import java.util.UUID;

public interface OutPutFireStationService {
  boolean isFireStationAlreadyInFile(Integer stationNumber, String address,
                                     ArrayList<OutPutFireStation> firestations
  );

  int getStationNumberByHomeId (UUID homeId, ArrayList<OutPutFireStation> fireStations);

  ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations);

  ArrayList<OutPutFireStation> getFiresStations();

  OutPutFireStation getFireStationByNumber(ArrayList<OutPutFireStation> fireStations,
                                           Integer stationNumber);

  ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                       ArrayList<Integer> stationNumber);

  ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                        ArrayList<OutPutHome> homes);

  OutPutFireStation setHome(OutPutFireStation fireStation,
                            ArrayList<OutPutHome> homes);
}
