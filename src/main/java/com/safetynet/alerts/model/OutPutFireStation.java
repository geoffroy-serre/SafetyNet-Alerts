package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class OutPutFireStation {
  @JsonProperty("station")
  private int stationNumber;
  @JsonProperty("home")
  private ArrayList<OutPutHome> homeList = new ArrayList<OutPutHome>();

  public void addWorkingHome(OutPutHome workingHome) {
    homeList.add(workingHome);
  }

  public int getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
  }

  public ArrayList<OutPutHome> getHomeList() {
    return homeList;
  }

  public void setHomeList(ArrayList<OutPutHome> homeList) {
    this.homeList = homeList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutPutFireStation that = (OutPutFireStation) o;
    return stationNumber == that.stationNumber &&
            homeList.equals(that.homeList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stationNumber, homeList);
  }
}
