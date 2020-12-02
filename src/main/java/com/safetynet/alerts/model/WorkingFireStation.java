package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class WorkingFireStation {
  @JsonProperty("idFirestation")
  private UUID idFireStation;
  @JsonProperty("station")
  private String stationNumber;
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

  public String getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(String stationNumber) {
    this.stationNumber = stationNumber;
  }

  public ArrayList<WorkingHome> getHomeList() {
    return homeList;
  }

  public void setHomeList(ArrayList<WorkingHome> homeList) {
    this.homeList = homeList;
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
