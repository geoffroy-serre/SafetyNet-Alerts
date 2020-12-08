package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WorkingFireStation {
  @JsonProperty("idFirestation")
  private UUID idFireStation;
  @JsonProperty("station")
  private int stationNumber;
  @JsonProperty("home")
  private ArrayList<WorkingHome> homeList = new ArrayList<WorkingHome>();

  public void addWorkingHome(WorkingHome workingHome) {
    homeList.add(workingHome);
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

  public ArrayList<WorkingHome> getHomeList() {
    return homeList;
  }

  public void setHomeList(ArrayList<WorkingHome> homeList) {
    this.homeList = homeList;
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
    Objects.equals(homeList, workingFireStation.homeList);




  }

  @Override
  public int hashCode() {
    return Objects.hash(stationNumber,idFireStation,homeList);
  }


  @Override
  public String toString() {
    return "WorkingFireStation{"
            + "idFireStation=" + idFireStation
            + ", stationNumber=" + stationNumber
            + ", homeList=" + homeList
            + '}';
  }
}
