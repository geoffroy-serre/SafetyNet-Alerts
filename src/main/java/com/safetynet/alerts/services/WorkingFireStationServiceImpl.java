package com.safetynet.alerts.services;

import com.safetynet.alerts.model.OriginalFirestation;
import com.safetynet.alerts.model.OriginalResponse;
import com.safetynet.alerts.model.WorkingFireStation;
import com.safetynet.alerts.model.WorkingHome;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkingFireStationServiceImpl {

  @Autowired
  OriginalResponse originalResponse;

  ArrayList<WorkingFireStation> workingFireStations = new ArrayList<WorkingFireStation>();

  /**
   * Create List of Working FireStation with Original FireStation list
   * created by calling originalResponse.getFirestations();
   *
   * @return ArrayList<WorkingFireStation>
   */
  public ArrayList<WorkingFireStation> createWorkingFireStations() {
    ArrayList<OriginalFirestation> originalFirestations = originalResponse.getFirestations();
    for (OriginalFirestation currentOriginalFirestation : originalFirestations) {
      WorkingFireStation workingFireStation = new WorkingFireStation();
      UUID alreadyPresentFireStationId =
              presentFireStationId(currentOriginalFirestation.getStation());

      if (alreadyPresentFireStationId != null) {
        for (WorkingFireStation presentFireStation : workingFireStations) {
          if (presentFireStation.getIdFireStation().equals(alreadyPresentFireStationId)) {
            presentFireStation.addWorkingHome(workingHomeServiceImpl.searchWorkingHome(currentOriginalFirestation.getAddress(), workingHomes));
          }
        }
      } else {
        workingFireStation.setIdFireStation(UUID.randomUUID());
        workingFireStation.setStationNumber(currentOriginalFirestation.getStation());
        WorkingHome truc =
                workingHomeServiceImpl.searchWorkingHome(currentOriginalFirestation.getAddress(),
                        workingHomes);
        workingFireStation.addWorkingHome(truc);
        workingFireStations.add(workingFireStation);
      }

    }
    return workingFireStations;
  }

  /**
   * Return ID of FireStation if it is in workingFirestations.
   * Else return null
   *
   * @param stationNumber String
   * @return fireStationID UUID
   */
  public UUID presentFireStationId(String stationNumber) {

    UUID idFireStationPresent = null;
    for (WorkingFireStation currentFireStation : workingFireStations) {
      if (currentFireStation.getStationNumber().equals(stationNumber)) {
        idFireStationPresent = currentFireStation.getIdFireStation();
      }
    }
    return idFireStationPresent;
  }
}
