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
  private int stationNumber;
  @JsonProperty("homeList")
  private ArrayList<WorkingHome> homeList;

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
  public String toString() {
    return "WorkingFireStation{"
            + "idFireStation=" + idFireStation
            + ", stationNumber=" + stationNumber
            + ", homeList=" + homeList
            + '}';
  }
}
