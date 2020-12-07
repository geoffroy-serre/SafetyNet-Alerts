package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class OriginalFirestation {

  @JsonProperty("address")
  private String address;

  @JsonProperty("station")
  private int station;

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setStation(int station) {
    this.station = station;
  }

  public int getStation() {
    return station;
  }


  @Override
  public String toString() {
    return
            "FirestationsItem{"
                    + "address = '" + address + '\''
                    + ",station = '" + station + '\''
                    + "}";
  }
}