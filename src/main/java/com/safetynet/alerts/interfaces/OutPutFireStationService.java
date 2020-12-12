package com.safetynet.alerts.interfaces;

import com.safetynet.alerts.model.OutPutFireStation;
import com.safetynet.alerts.model.OutPutHome;
import java.util.ArrayList;

public interface OutPutFireStationService {
  ArrayList<OutPutFireStation> setStationNumberHomesToNull(ArrayList<OutPutFireStation> fireStations);

  ArrayList<OutPutFireStation> getFiresStations();

  OutPutFireStation getFireStationByNumber(ArrayList<OutPutFireStation> fireStations,
                                           Integer stationNumber);

  ArrayList<OutPutFireStation> getFireStationByNumbers(ArrayList<OutPutFireStation> fireStations,
                                                       ArrayList<Integer> stationNumber);

  ArrayList<OutPutFireStation> setHomes(ArrayList<OutPutFireStation> fireStations,
                                        ArrayList<OutPutHome> homes);
}
