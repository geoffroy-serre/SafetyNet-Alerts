package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
/**
 * Working format data for
 * @param UUID idFireStation;
 * @param int stationNumber;
 * @param ArrayList<UUID> workingHomeIds = new ArrayList<UUID>();
 */
public class WorkingFireStation {
  @JsonProperty("idFirestation")
  private UUID idFireStation;
  @JsonProperty("station")
  private int stationNumber;
  @JsonProperty("home")
  private ArrayList<UUID> workingHomeIds = new ArrayList<UUID>();

  public void addWorkingHome(UUID workingHomeId) {
    workingHomeIds.add(workingHomeId);
  }

  public UUID getIdFireStation() {
    return idFireStation;
  }

  public void setIdFireStation(UUID idFireStation) {
    this.idFireStation = idFireStation;
  }

  public int getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
  }

  public ArrayList<UUID> getWorkingHomeIds() {
    return workingHomeIds;
  }

  public void setWorkingHomeIds(ArrayList<UUID> workingHomeIds) {
    this.workingHomeIds = workingHomeIds;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) return true;
    if (!(obj instanceof WorkingFireStation)) {
      return false;
    }
    WorkingFireStation workingFireStation = (WorkingFireStation) obj;
    return Objects.equals(stationNumber, workingFireStation.stationNumber)&&
    Objects.equals(idFireStation, workingFireStation.idFireStation)&&
    Objects.equals(workingHomeIds, workingFireStation.workingHomeIds);




  }

  @Override
  public int hashCode() {
    return Objects.hash(stationNumber,idFireStation,workingHomeIds);
  }


  @Override
  public String toString() {
    return "WorkingFireStation{"
            + "idFireStation=" + idFireStation
            + ", stationNumber=" + stationNumber
            + ", homeList=" + workingHomeIds
            + '}';
  }
}
