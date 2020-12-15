package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DeleteFirestation {
  @JsonProperty("address")
  private String address;

  @JsonProperty("station")
  private Integer station;

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setStation(Integer station) {
    this.station = station;
  }

  public Integer getStation() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeleteFirestation that = (DeleteFirestation) o;
    return address.equals(that.address) &&
            station.equals(that.station);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, station);
  }
}
