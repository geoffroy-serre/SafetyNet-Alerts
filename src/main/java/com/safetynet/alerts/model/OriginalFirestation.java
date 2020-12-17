package com.safetynet.alerts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Component
/**
 * Original data format
 * @param String address;
 * @param Integer station;
 */
public class OriginalFirestation {

  @JsonProperty("address")
  @NotBlank
  @NotNull
  private String address;

  @JsonProperty("station")
  @NotNull
  private Integer station;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getStation() {
    return station;
  }

  public void setStation(Integer station) {
    this.station = station;
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
    OriginalFirestation that = (OriginalFirestation) o;
    return address.equals(that.address) &&
            station.equals(that.station);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, station);
  }
}