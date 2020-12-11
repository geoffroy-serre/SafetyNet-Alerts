package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class OutPutFireStation {
  @JsonProperty("idFirestation")
  private UUID idFirestation;
  @JsonProperty("station")
  private int stationNumber;
  @JsonProperty("home")
  private ArrayList<UUID> homeListIds = new ArrayList<UUID>();

  @JsonProperty("homeList")
  private ArrayList<OutPutHome> homes = new ArrayList<>();


  public ArrayList<OutPutHome> getHomes() {
    return homes;
  }

  public void setHomes(ArrayList<OutPutHome> homes) {
    this.homes = homes;
  }
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public ArrayList<UUID> getHomeListIds() {
    return homeListIds;
  }

  public void setHomeListIds(ArrayList<UUID> homeListIds) {
    this.homeListIds = homeListIds;
  }
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  public UUID getIdFirestation() {
    return idFirestation;
  }

  public void setIdFirestation(UUID idFirestation) {
    this.idFirestation = idFirestation;
  }



  public int getStationNumber() {
    return stationNumber;
  }

  public void setStationNumber(int stationNumber) {
    this.stationNumber = stationNumber;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OutPutFireStation that = (OutPutFireStation) o;
    return stationNumber == that.stationNumber &&
            homeListIds.equals(that.homeListIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stationNumber, homeListIds);
  }
}
